package com.rapid7.sdlc.plugin;

import com.rapid7.container.analyzer.docker.model.image.ImageId;
import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.jenkins.ReportCreationException;
import com.rapid7.sdlc.plugin.ruleset.Rule;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Provides functionality for generating an HTML report of assessment results.
 */
public abstract class ReportService {

    /**
     * Generates an HTML report of the given results.
     *
   * @param image The assessment report retrieved from
     *         Rapid7's API.
   * @param ruleResults A list of pairs of rules and booleans,
     *         with each bool being <code>true</code>
     *         if the corresponding rule was triggered
     *         and <code>false</code> otherwise.
   * @param staticContentDir The relative path to the static content
     *         directory for each CI platform.
     *         E.g. `/jenkins/container-assessment` for Jenkins
     *         Note that by convention:
     *         1) images: ${staticContentDir}/images
     *         2) css: ${staticContentDir}/css
     *         3) scrips: ${staticContentDir}/js
     *
     * @return A string representation of the report
     */
    public String generateAssessmentReport(final String projectName, final int buildNumber, final String cause,
                                           final ImageId imageId, final String imageName, final Image image,
                                           final Map<Rule, RuleResult> ruleResults, final String staticContentDir)
            throws ReportCreationException {
        final Map<String, Object> data = procureData(projectName, buildNumber, cause, imageId, imageName, image,
                                                     ruleResults, staticContentDir);
        return report(data);
    }

    abstract String report(Map<String, Object> data) throws ReportCreationException;

    private Map<String, Object> procureData(final String projectName, final int buildNumber, final String cause,
                                            final ImageId imageId, final String imageName, final Image image,
                                            final Map<Rule, RuleResult> ruleResults, final String staticContentDir) {
        final List<Package> vulnerablePackages = image.getPackages()
                                                      .stream()
                                                      .filter(pkg -> pkg.getAssessment()
                                                                        .getVulnerabilities()
                                                                        .getTotal() > 0)
                                                      .collect(toList());
        final Map<Long, Set<Rule>> failingPackages = getFailingPackages(ruleResults.entrySet()
                                                                                   .stream()
                                                                                   .filter(e -> e.getValue()
                                                                                                 .failed())
                                                                                   .map(Map.Entry::getKey)
                                                                                   .collect(toList()), image);
        final Map<String, Object> data = new LinkedHashMap<>();

        // Export image data
        data.put("imageId", imageId.getString());
        data.put("imageName", imageName);
        data.put("buildNumber", buildNumber);

        // Export assessment result data
        data.put("vulnerablePackageCount", vulnerablePackages.size());
        data.put("projectName", projectName);
        data.put("cause", cause);
        data.put("failed", ruleResults.values()
                                      .stream()
                                      .anyMatch(RuleResult::failed));
        data.put("failedRuleCount", ruleResults.values()
                                               .stream()
                                               .filter(RuleResult::failed)
                                               .count());
        data.put("passedRuleCount", ruleResults.values()
                                               .stream()
                                               .filter(e -> !e.failed())
                                               .count());
        data.put("image", image);
        data.put("dateInMillis", Instant.now()
                                        .toEpochMilli());
        data.put("packageAssessmentsAffectingPolicy", image.getPackages()
                                                           .stream()
                                                           .filter(a -> failingPackages.containsKey(a.getId()))
                                                           .sorted(Comparator.comparing(Package::getName))
                                                           .collect(toList()));

        // Export rules
        // sort by status
        data.put("ruleResults", ruleResults.entrySet()
                                           .stream()
                                           .sorted((r1, r2) -> Boolean.compare(r2.getValue()
                                                                                 .failed(), r1.getValue()
                                                                                              .failed()))
                                           .collect(toList()));
        data.put("otherPackageAssessments", vulnerablePackages.stream()
                                                              .filter(a -> !failingPackages.containsKey(a.getId()))
                                                              .sorted(Comparator.comparing(Package::getName))
                                                              .collect(toList()));

        // Export static content directory
        data.put("staticContentDir", staticContentDir);
        return data;
    }

    /**
   * @param rules A list of rules against which to check packages. All are assumed to have been triggered.
   * @param image The assessment report retrieved from Rapid7's API.
     * @return A map of failing packages, with each package mapping to a Set of the rules it triggered.
     */
  private Map<Long, Set<Rule>> getFailingPackages(List<Rule> rules, com.rapid7.sdlc.plugin.api.model.Image image) {
    Map<Long, Set<Rule>> ret = new HashMap<>();

        // This probably *could* be one giant pure-functional return statement, but... no.
        rules.forEach(rule -> rule.getPropertyEvaluator()
                                  .getQualifyingPackages(image)
                                  .forEach(pkg -> {
      Long key = pkg.getId();
      if (!ret.containsKey(key))
                                          ret.put(key, new HashSet<>());

                                      ret.get(key)
                                         .add(rule);
                                  }));

        return ret;
    }
}
