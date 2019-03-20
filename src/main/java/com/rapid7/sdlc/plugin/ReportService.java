package com.rapid7.sdlc.plugin;

import com.rapid7.container.analyzer.docker.model.image.ImageId;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.Rule;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import static java.util.stream.Collectors.toList;

/**
 * Provides functionality for generating an HTML report of assessment results.
 */
public class ReportService {

  private static final String TEMPLATE_FILE = "reportTemplate.ftl";
  private Template template;

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
  public ReportService(File buildDir) throws IOException {

    Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    File buildTemplate = new File(buildDir, TEMPLATE_FILE);
    try {
      // First copy the template into the build directory where it could be found
      InputStream templateData = getClass().getClassLoader().getResourceAsStream(TEMPLATE_FILE);

      FileUtils.copyInputStreamToFile(templateData, buildTemplate);

      // Load build template from build folder
      cfg.setDirectoryForTemplateLoading(buildDir);
      template = cfg.getTemplate(TEMPLATE_FILE);
    } finally {
      //noinspection ResultOfMethodCallIgnored
      buildTemplate.delete();
    }
  }

  /**
   * Generates an HTML report of the given results.
   *
   * @param image The assessment report retrieved from
   *        Rapid7's API.
   * @param ruleResults A list of pairs of rules and booleans,
   *        with each bool being <code>true</code>
   *        if the corresponding rule was triggered
   *        and <code>false</code> otherwise.
   * @param staticContentDir The relative path to the static content
   *        directory for each CI platform.
   *        E.g. `/jenkins/container-assessment` for Jenkins
   *        Note that by convention:
   *        1) images: ${staticContentDir}/images
   *        2) css: ${staticContentDir}/css
   *        3) scrips: ${staticContentDir}/js
   * @return A string representation of the report
   * @throws IOException If the template file cannot be read.
   * @throws TemplateException If an error occurs while processing
   *         the template.
   */
  public String generateAssessmentReport(String projectName, int buildNumber, String cause, ImageId imageId, String imageName, com.rapid7.sdlc.plugin.api.model.Image image, Map<Rule, RuleResult> ruleResults, String staticContentDir) throws IOException, TemplateException {
    List<Package> vulnerablePackages = image.getPackages().stream().filter(pkg -> pkg.getAssessment().getVulnerabilities().getTotal() > 0).collect(toList());
    Map<Long, Set<Rule>> failingPackages = getFailingPackages(ruleResults.entrySet().stream().filter(e -> e.getValue().failed()).map(Map.Entry::getKey).collect(toList()), image);
    Map<String, Object> data = new HashMap<>();

    // Export image data
    data.put("imageId", imageId.getString());
    data.put("imageName", imageName);
    data.put("buildNumber", buildNumber);

    // Export assessment result data
    data.put("vulnerablePackageCount", vulnerablePackages.size());
    data.put("projectName", projectName);
    data.put("cause", cause);
    data.put("failed", ruleResults.values().stream().anyMatch(RuleResult::failed));
    data.put("failedRuleCount", ruleResults.values().stream().filter(RuleResult::failed).count());
    data.put("passedRuleCount", ruleResults.values().stream().filter(e -> !e.failed()).count());
    data.put("image", image);
    data.put("dateInMillis", Instant.now().toEpochMilli());
    data.put("packageAssessmentsAffectingPolicy", image.getPackages().stream()
        .filter(a -> failingPackages.containsKey(a.getId()))
        .sorted(Comparator.comparing(Package::getName))
        .collect(toList()));

    // Export rules
    // sort by status
    data.put("ruleResults", ruleResults.entrySet().stream().sorted((r1, r2) -> Boolean.compare(r2.getValue().failed(), r1.getValue().failed())).collect(toList()));
    data.put("otherPackageAssessments", vulnerablePackages.stream()
        .filter(a -> !failingPackages.containsKey(a.getId()))
        .sorted(Comparator.comparing(Package::getName))
        .collect(toList()));

    // Export static content directory
    data.put("staticContentDir", staticContentDir);

    // Render template
    Writer dataWriter = new StringWriter();
    template.process(data, dataWriter);
    dataWriter.flush();

    return dataWriter.toString();
  }

  /**
   * @param rules A list of rules against which to check packages. All are assumed to have been triggered.
   * @param image The assessment report retrieved from Rapid7's API.
   * @return A map of failing packages, with each package mapping to a Set of the rules it triggered.
   */
  private Map<Long, Set<Rule>> getFailingPackages(List<Rule> rules, com.rapid7.sdlc.plugin.api.model.Image image) {
    Map<Long, Set<Rule>> ret = new HashMap<>();

    // This probably *could* be one giant pure-functional return statement, but... no.
    rules.forEach(rule -> rule.getPropertyEvaluator().getQualifyingPackages(image).forEach(pkg -> {
      Long key = pkg.getId();
      if (!ret.containsKey(key))
        ret.put(key, new HashSet<>());

      ret.get(key).add(rule);
    }));

    return ret;
  }
}
