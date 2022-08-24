package com.rapid7.sdlc.plugin.jenkins;

import com.cloudbees.plugins.credentials.CredentialsMatchers;
import com.cloudbees.plugins.credentials.CredentialsProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapid7.container.analyzer.docker.model.image.Image;
import com.rapid7.container.analyzer.docker.model.image.ImageId;
import com.rapid7.container.analyzer.docker.model.image.json.ImageModelObjectMapper;
import com.rapid7.container.analyzer.docker.service.DockerImageAnalyzerService;
import com.rapid7.sdlc.plugin.AssessmentService;
import com.rapid7.sdlc.plugin.HtmlReportService;
import com.rapid7.sdlc.plugin.JsonReportService;
import com.rapid7.sdlc.plugin.ReportService;
import com.rapid7.sdlc.plugin.Status;
import com.rapid7.sdlc.plugin.api.BuildApi;
import com.rapid7.sdlc.plugin.api.client.ApiClient;
import com.rapid7.sdlc.plugin.api.model.NewImageBuild;
import com.rapid7.sdlc.plugin.api.model.Policy;
import com.rapid7.sdlc.plugin.api.model.Rule.ActionEnum;
import com.rapid7.sdlc.plugin.api.model.Rule.CriterionEnum;
import com.rapid7.sdlc.plugin.api.model.Rule.StatusEnum;
import com.rapid7.sdlc.plugin.jenkins.InsightVmApiConfiguration.Region;
import com.rapid7.sdlc.plugin.jenkins.report.AssessmentReportBaseAction;
import com.rapid7.sdlc.plugin.jenkins.report.AssessmentReportProjectAction;
import com.rapid7.sdlc.plugin.jenkins.report.AssessmentReportRunAction;
import com.rapid7.sdlc.plugin.ruleset.Rule;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;
import com.rapid7.sdlc.plugin.ruleset.action.FailAction;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.AbortException;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Functions;
import hudson.Launcher;
import hudson.ProxyConfiguration;
import hudson.model.AbstractProject;
import hudson.model.Cause;
import hudson.model.Item;
import hudson.model.Node;
import hudson.model.Result;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.security.ACL;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.ArgumentListBuilder;
import hudson.util.FormValidation;
import hudson.util.Secret;
import jenkins.model.Jenkins;
import jenkins.security.MasterToSlaveCallable;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.docker.commons.tools.DockerTool;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import static com.cloudbees.plugins.credentials.CredentialsMatchers.withId;
import static hudson.Util.fixNull;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang.StringUtils.trimToEmpty;

/**
 * A build step to assess Docker containers via the Rapid7 platform. This task performs the
 * following steps. 1. Analyzes the provided image 2. Submits the analysis to Rapid7 for assessment
 * 3. Produces an HTML-formatted vulnerability report
 */
public class ContainerAssessmentBuilder extends Builder implements SimpleBuildStep {
  public static final String PLUGIN_NAME = "rapid7-insightvm-container-assessment";
  private ArrayList<Rule> rawRules = new ArrayList<>();
  private List<ThresholdRuleDescribable> thresholdRules = Collections.emptyList();
  private List<NameRuleDescribable> nameRules = Collections.emptyList();
  private Map<String, ThresholdRuleDescribable> uniqueThresholdRules = new HashMap<>();
  private boolean failOnPluginError;
  private boolean treatWarningsAsErrors;
  private String imageId;
  private String workspaceDir;
  private ApiClient apiClient;
  private boolean writeReportToWorkspace;
  private String workspaceFilename;

  @DataBoundConstructor
  public ContainerAssessmentBuilder() {
    super();
  }

  public String getImageId() {
    return imageId;
  }

  @DataBoundSetter
  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getWorkspaceDir() {
    return workspaceDir;
  }

  @DataBoundSetter
  public void setWorkspaceDir(String workspaceDir) {
    this.workspaceDir = workspaceDir;
  }

  public boolean getFailOnPluginError() {
    return failOnPluginError;
  }

  @DataBoundSetter
  public void setFailOnPluginError(boolean failOnPluginError) {
    this.failOnPluginError = failOnPluginError;
  }

  public boolean getTreatWarningsAsErrors() {
    return treatWarningsAsErrors;
  }

  @DataBoundSetter
  public void setTreatWarningsAsErrors(boolean treatWarningsAsErrors) {
    this.treatWarningsAsErrors = treatWarningsAsErrors;
  }

  public List<ThresholdRuleDescribable> getThresholdRules() {
    return thresholdRules;
  }

    @DataBoundSetter
    public void setThresholdRules(List<ThresholdRuleDescribable> rules) {
        this.thresholdRules = rules;
        rules.forEach(r -> uniqueThresholdRules.putIfAbsent(r.getClass().getName(), r));
        uniqueThresholdRules.values().forEach(ruleDescriptor -> rawRules.add(new Rule(ruleDescriptor.getActionObject(), ruleDescriptor.getPropertyEvaluator())));
    }

  public List<NameRuleDescribable> getNameRules() {
    return nameRules;
  }

  @DataBoundSetter
  public void setNameRules(List<NameRuleDescribable> rules) {
    this.nameRules = rules;
    rules.forEach(ruleDescriptor -> rawRules.add(new Rule(ruleDescriptor.getActionObject(), ruleDescriptor.getPropertyEvaluator())));
  }

  public boolean getWriteReportToWorkspace() {
    return writeReportToWorkspace;
  }

  @DataBoundSetter
  public void setWriteReportToWorkspace(final boolean writeReportToWorkspace) {
    this.writeReportToWorkspace = writeReportToWorkspace;
  }

  public String getWorkspaceFilename() {
    return workspaceFilename;
  }

  @DataBoundSetter
  public void setWorkspaceFilename(final String workspaceFilename) {
    this.workspaceFilename = workspaceFilename;
  }

  @SuppressFBWarnings("REC_CATCH_EXCEPTION")
  @Override
  public void perform(@Nonnull Run<?, ?> build, @Nonnull FilePath workspace, @Nonnull Launcher launcher, @Nonnull TaskListener listener) {
    final PrintStream logger = listener.getLogger();
    logger.println("Starting Rapid7 InsightVM Container Assessment");
    FilePath sdlcTemp = null;
    try {
      // Fail fast if the only rules defined are invalid
      if (!rawRules.isEmpty() && rawRules.stream().filter(rule -> rule.getPropertyEvaluator().isValid()).count() < 1)
        throw new AbortException("All of the defined rules are invalid.");

      StringCredentials apiKey = null;
      String credentialsId = InsightVmApiConfiguration.get().getCredentialsId();
      if (credentialsId != null) {
        apiKey = CredentialsMatchers.firstOrNull(
            CredentialsMatchers.filter(
                CredentialsProvider.lookupCredentials(StringCredentials.class, Jenkins.getInstance(), ACL.SYSTEM, Collections.emptyList()),
                withId(trimToEmpty(credentialsId))),
            CredentialsMatchers.allOf(
                CredentialsMatchers.withId(credentialsId)
            ));
      }
      if (apiKey == null)
        throw new AbortException("InsightVM API key is missing. Please configure it before using the container assessment plugin.");

      String nodeName = System.getenv("NODE_NAME");
      Node node = null;
      if (nodeName != null && !nodeName.isEmpty())
        node = Jenkins.getInstance().getNode(nodeName);

      EnvVars environment = build.getEnvironment(listener);
      String expandedImageId = environment.expand(imageId);
      String dockerCommand = DockerTool.getExecutable("docker", node, launcher.getListener(), environment);
      boolean useSudo = Boolean.parseBoolean(environment.get("USE_SUDO_DOCKER", "false"));

      // Setup temp directory in workspace
      sdlcTemp = workspace.createTempDir("r7sdlc", null);

      // Find image ID
      logger.println("Retrieving image ID from Docker...");
      ArgumentListBuilder args = new ArgumentListBuilder().add(dockerCommand).add("image").add("ls").add("--no-trunc").add(expandedImageId);
      if (useSudo)
        args.prepend("sudo");

      Launcher.ProcStarter procStarter = launcher.launch();
      procStarter.pwd(sdlcTemp);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ByteArrayOutputStream err = new ByteArrayOutputStream();
      procStarter.cmds(args).envs(environment).stdout(out).stderr(err);
      int status = procStarter.start().joinWithTimeout(10, TimeUnit.SECONDS, launcher.getListener());
      if (status != 0)
        throw new AbortException("Docker command exited with status " + status + ": " + err.toString(StandardCharsets.UTF_8.name()));

      String dockerImageId = null;
      try (Reader r = new StringReader(out.toString(StandardCharsets.UTF_8.name()));
           BufferedReader in = new BufferedReader(r)) {
        String line;
        in.readLine(); // ls header
        while ((line = in.readLine()) != null) {
          final StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
          if (stringTokenizer.countTokens() < 5) {
            throw new AbortException("Unexpected `docker image ls` output : " + line);
          }
          String repo = stringTokenizer.nextToken(); // Repo
          String tag = stringTokenizer.nextToken(); // Tag
          String repoTag = String.join(":", repo, tag);
          if (expandedImageId.equalsIgnoreCase(repoTag)) {
            dockerImageId = stringTokenizer.nextToken(); // Image ID
            break;
          }
        }
      }

      out.reset();
      err.reset();
      args.clear();

      if (dockerImageId == null || dockerImageId.isEmpty())
        throw new AbortException("No images matching '" + expandedImageId + "' found, aborting.");

      // Export image tar file
      logger.println("Creating image bundle...");
      args.add(dockerCommand, "save", dockerImageId, "-o", "image.tar");
      if (useSudo)
        args.prepend("sudo");

      status = procStarter.cmds(args).envs(environment).stdout(out).stderr(err).start().join();
      if (status != 0)
        throw new AbortException("Docker command exited with status " + status + ": " + err.toString(StandardCharsets.UTF_8.name()));

      // Execute image analyzer on remote agent where docker image was built
      AnalyzeCallable analyzer = new AnalyzeCallable(sdlcTemp, InsightVmApiConfiguration.get().getRpmDockerImage(), launcher.getListener());
      logger.println("Analyzing image " + expandedImageId + "...");
      try {
        Objects.requireNonNull(launcher.getChannel()).call(analyzer);
      } catch (NullPointerException nullPointerException) {
        logger.println("No channel found for launch. Unable to call analyzer.");
      }
      FilePath imageJson = sdlcTemp.child("image.json");
      Image image = null;
      if (imageJson.exists()) {
        ObjectMapper mapper = new ImageModelObjectMapper();
        try {
          image = mapper.readValue(imageJson.readToString(), Image.class);
        } catch (Exception exception) {
          StringBuilder message = new StringBuilder(exception.toString());
          message.append("\n");
          Arrays.stream(exception.getStackTrace()).forEach(line -> message.append('\t').append(line.toString()).append('\n'));
          logger.println(message.toString());
          throw new AbortException("Image analysis failed: image.json is not valid.");
        }
      }
      else {
        throw new AbortException("Image analysis failed: image.json not found in workspace.");
      }

      logger.println("Image ID: " + image.getId());
      logger.println("Packages: " + image.getPackages().size());

      Region region = InsightVmApiConfiguration.get().getInsightRegion();
      logger.println("Using InsightVM in region " + region.getLabel() + " (" + region.getEndpoint() + ")");
      apiClient = new ApiClient("api-key", Secret.toString(apiKey.getSecret()));
      apiClient.setBasePath(InsightVmApiConfiguration.get().getInsightRegion().getEndpoint());
      ProxyConfiguration proxyCfg = Jenkins.getInstance().proxy;
      if (proxyCfg != null)
        apiClient.setProxy(proxyCfg);

      AssessmentService assessmentService = new AssessmentService(apiClient);
      logger.println("Submitting image for assessment...");
      assessmentService.uploadImageAnalysis(image);
      logger.println("Retrieving assessment results...");
      com.rapid7.sdlc.plugin.api.model.Image assessedImage = assessmentService.getImage(image.getId());

      logger.println("Evaluating build policy...");
      Map<Rule, RuleResult> ruleResults = evaluateRules(assessedImage, logger);

      logger.println("Generating report...");
      generateReport(build, image.getId(), expandedImageId, assessedImage, ruleResults, workspace);

      logger.println("Executing rule actions...");
      Status buildStatus = takeRuleActions(build, listener, ruleResults);

      submitMetrics(build, ruleResults, image.getId(), buildStatus, InsightVmApiConfiguration.get().getSystemId());

    } catch (Exception exception) {
      logger.println("Encountered an exception.");
      exception.printStackTrace(logger);
      if (failOnPluginError)
        build.setResult(Result.FAILURE);
      else
        logger.println("Continuing anyway because 'fail on plugin failure' is disabled...");
    } finally {
      logger.println("Cleaning up temporary files in workspace...");
      try {
        if (sdlcTemp != null)
          sdlcTemp.deleteRecursive();
      } catch (Exception exception) {
        logger.println("Cleaning up temporary files in workspace failed.");
        exception.printStackTrace(logger);
      }
    }
    logger.println("Image assessment completed.");
  }

  @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
  private void submitMetrics(final Run<?, ?> build, Map<Rule, RuleResult> ruleResults, ImageId imageId, Status buildStatus, String systemId) {
    @SuppressWarnings("ConstantConditions")
    final String versionNumber = fixNull(Jenkins.getVersion().toString());
    NewImageBuild buildResource = new NewImageBuild()
        .artifactId(imageId.getString())
        .number(String.valueOf(build.getNumber()))
        .platform("JENKINS")
        .policy(ruleResults == null || ruleResults.isEmpty() ? new Policy() : new Policy().rules(ruleResults.entrySet().stream().map(this::convert).collect(toList())))
        .projectId(build.getParent().getFullDisplayName())
        .start(build.getTime().toInstant().toString())
        .status(buildStatus.name())
        .systemId(systemId)
        .version(versionNumber);

    apiClient.buildClient(BuildApi.class).newImageBuild(buildResource);
  }

  private com.rapid7.sdlc.plugin.api.model.Rule convert(Entry<Rule, RuleResult> ruleResult) {
    Rule rule = ruleResult.getKey();
    RuleResult result = ruleResult.getValue();

    return new com.rapid7.sdlc.plugin.api.model.Rule()
        .action(ActionEnum.fromValue(rule.getAction().getType().name()))
        .criterion(CriterionEnum.fromValue(rule.getPropertyEvaluator().getCriterionName()))
        .configuredValue(rule.getPropertyEvaluator().getConfiguredValue())
        .status(result.failed() ? StatusEnum.FAILED : StatusEnum.PASSED)
        .actualValue(result.getActualValue());
  }

  /**
   * Checks an assessment result to determine which of the configured rules were triggered.
   *
   * @param assessment A container assessment result retrieved from the Rapid7 API.
   * @return For all rules, a list of pairs containing (a) a rule and (b) a boolean indicating
   *         whether the rule was triggered.
   */
  private Map<Rule, RuleResult> evaluateRules(com.rapid7.sdlc.plugin.api.model.Image assessment, PrintStream logger) {
    rawRules.stream()
        .filter(rule -> !rule.isValid())
        .forEach(v -> logger.println("WARN: Skipping invalid rule: " + v.getPropertyEvaluator().getDisplayName() + " " + v.getPropertyEvaluator().getConfiguredValue()));

    if (rawRules.stream().filter(Rule::isValid).count() < 1) {
      logger.println("WARN: No valid rules defined.");
      return emptyMap();
    }

    return rawRules.stream().filter(Rule::isValid).collect(toMap(r -> r, r -> r.check(assessment)));
  }

  /**
   * Given a list of rule-boolean pairs, executes the actions for those rules whose corresponding
   * boolean is true.
   *
   * @param build The Jenkins build context.
   * @param listener The Jenkins task listener.
   * @param ruleResults A list of rule-boolean pairs where each boolean indicates whether the
   *        corresponding rule was triggered.
   * @return A TaskResult indicating failure or success.
   */
  private Status takeRuleActions(Run<?, ?> build, TaskListener listener, Map<Rule, RuleResult> ruleResults) {
    ArrayList<String> failMsgs = new ArrayList<>();
    ArrayList<String> warnMsgs = new ArrayList<>();

    for (Map.Entry<Rule, RuleResult> entry : ruleResults.entrySet()) {
      Rule rule = entry.getKey();
      if (entry.getValue().failed()) {
        if (rule.getAction() instanceof FailAction) {
          failMsgs.add(rule.getMessage());
          listener.getLogger().println("ERROR: " + rule.getMessage());
        } else {
          warnMsgs.add(rule.getMessage());
          listener.getLogger().println("WARN: " + rule.getMessage());
        }
      }
    }

    if (!failMsgs.isEmpty() || treatWarningsAsErrors && !warnMsgs.isEmpty()) {
      build.setResult(Result.FAILURE);
      return Status.FAILURE;
    } else if (!warnMsgs.isEmpty()) {
      build.setResult(Result.UNSTABLE);
      return Status.UNSTABLE;
    } else {
      build.setResult(Result.SUCCESS);
      return Status.SUCCESS;
    }
  }

  /**
   * Generates an HTML report for a given build result.
   *
   * @param build The Jenkins build context.
   * @param image A container assessment result retrieved from the Rapid7 API.
   * @param ruleResults A list of rule-boolean pairs where each boolean indicates whether the
   *        corresponding rule was triggered.
   * @throws IOException If the report template cannot be read.
   * @throws ReportCreationException If the report template fails to process.
   */
  private void generateReport(Run<?, ?> build, ImageId imageId, String imageName, com.rapid7.sdlc.plugin.api.model.Image image, Map<Rule, RuleResult> ruleResults, FilePath workspace) throws IOException, ReportCreationException, InterruptedException {
    // TODO: utilize a reporting plugin abstract away all of this?
    File reportDir = new File(build.getRootDir(), AssessmentReportBaseAction.BASE_DIR);
    if (!reportDir.exists() && !reportDir.mkdir()) {
      throw new IOException("Report directory does not exist, and could not be created.");
    }

    Optional<String> startedBy = build.getCauses().stream().map(Cause::getShortDescription).findFirst();

    final File htmlReport = new File(reportDir, AssessmentReportBaseAction.REPORT_PAGE);
    try (final OutputStream outputStream = new FileOutputStream(htmlReport)) {
      final ReportService service = new HtmlReportService(reportDir);
      writeReport(build, imageId, imageName, image, ruleResults, startedBy, outputStream, service);
    }

    final File jsonReport = new File(reportDir, AssessmentReportBaseAction.REPORT_JSON);
    final ReportService service = new JsonReportService();
    try (final OutputStream outputStream = new FileOutputStream(jsonReport)) {
      writeReport(build, imageId, imageName, image, ruleResults, startedBy, outputStream, service);
    }

    if (writeReportToWorkspace) {
      final String reportfileName;
      if (null != workspaceFilename && !workspaceFilename.trim().isEmpty()) {
        reportfileName = workspaceFilename.trim();
      } else {
        reportfileName = "rapid7-report.json";
      }
      try (final OutputStream outputStream = new FilePath(workspace, reportfileName).write()) {
        writeReport(build, imageId, imageName, image, ruleResults, startedBy, outputStream, service);
      }
    }

      final AssessmentReportRunAction action = new AssessmentReportRunAction(reportDir);
      build.replaceAction(action);
    }

    private static void writeReport(final Run<?, ?> build, final ImageId imageId, final String imageName,
                                    final com.rapid7.sdlc.plugin.api.model.Image image, final Map<Rule, RuleResult> ruleResults,
                                    final Optional<String> startedBy, final OutputStream writer,
                                    final ReportService reportService) throws IOException, ReportCreationException {
        final String staticContentDir = Functions.getResourcePath() + "/plugin/" + PLUGIN_NAME + "/";
        final String result = reportService.generateAssessmentReport(build.getParent()
                        .getFullDisplayName(), build.getNumber(),
                startedBy.orElse("Unknown Cause"), imageId, imageName,
                image, ruleResults, staticContentDir);
        writer.write(result.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public hudson.model.Action getProjectAction(AbstractProject<?, ?> project) {
        return new AssessmentReportProjectAction(project);
    }

  @Override
  public DescriptorImpl getDescriptor() {
    return (DescriptorImpl) super.getDescriptor();
  }

  @Extension
  @Symbol("assessContainerImage")
  public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
    public DescriptorImpl() {
      load();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isApplicable(Class<? extends AbstractProject> type) {
      // Indicates that this builder can be used with all kinds of project types
      return true;
    }

    @Override
    @Nonnull
    public String getDisplayName() {
      return Messages.ContainerAssessmentBuilder_BuildStepName();
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
      req.bindJSON(this, formData);
      save();
      return super.configure(req, formData);
    }

    public FormValidation doCheckImageId(@QueryParameter String value, @AncestorInPath Item item) {
      if (item == null) {
        return FormValidation.ok();
      }
      item.checkPermission(Item.CONFIGURE);

      // skip check if not an admin
      if (!Jenkins.getInstance().hasPermission(Jenkins.ADMINISTER))
        return FormValidation.ok();

      String credentialsId = InsightVmApiConfiguration.get().getCredentialsId();
      StringCredentials apiKey = null;
      if (credentialsId != null) {
        apiKey = CredentialsMatchers.firstOrNull(
            CredentialsMatchers.filter(
                CredentialsProvider.lookupCredentials(StringCredentials.class, Jenkins.getInstance(), ACL.SYSTEM, Collections.emptyList()),
                withId(trimToEmpty(credentialsId))),
            CredentialsMatchers.allOf(
                CredentialsMatchers.withId(credentialsId) // TODO: More specificity for type/etc
            ));
      }

      if (apiKey == null)
        return FormValidation.error(Messages.ContainerAssessmentBuilder_ApiTokenMissing());

      return FormValidation.ok();
    }
  }

  private static final class AnalyzeCallable extends MasterToSlaveCallable<Void, Exception> {
    private static final long serialVersionUID = 1L;
    private final String rpmImageId;
    private final FilePath workDir;
    private final TaskListener taskListener;

    AnalyzeCallable(FilePath workDir, String rpmImageId, TaskListener taskListener) {
      this.rpmImageId = rpmImageId;
      this.workDir = workDir;
      this.taskListener = taskListener;
    }

    @Override
    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
    public Void call() throws IOException {
      PrintStream logger = taskListener.getLogger();
      File imageTar = new File(workDir.getRemote(), "image.tar");

      logger.println("Extracting image data...");
      File extractDir = new File(workDir.getRemote(), "/extract");
      if (!extractDir.exists())
        //noinspection ResultOfMethodCallIgnored
        extractDir.mkdir();

      logger.println("Analyzing image data...");
      DockerImageAnalyzerService analyzerService = new DockerImageAnalyzerService(rpmImageId);
      Image image = analyzerService.analyze(imageTar, workDir.getRemote() + "/extract");
      analyzerService.writeJson(image, new File(workDir.getRemote(), "image.json"));
      logger.println("Finished analyzing image data.");
      return null;
    }
  }

}
