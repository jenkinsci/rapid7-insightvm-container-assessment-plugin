package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.api.model.Vulnerability.SeverityEnum;
import com.rapid7.sdlc.plugin.ruleset.CriterionName;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * A property that is satisfied if the number of moderate vulnerabilities
 * in the assessment report exceeds the threshold.
 */
public class ModerateVulnerabilities extends IntegerPropertyEvaluator {

  public static final String displayName = "Moderate vulnerability count exceeds";

  public ModerateVulnerabilities() {
  }

  public ModerateVulnerabilities(int threshold) {
    super(threshold);
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public int getValue(Image image) {
    return image.getAssessment().getVulnerabilities().getSeverity().getModerate();
  }

  @Override
  public Set<Package> getQualifyingPackages(Image image) {
    return image.getPackages().stream().filter(pkg -> pkg.getAssessment().getFindings().stream().anyMatch(finding -> finding.getVulnerability().getSeverity() == SeverityEnum.MODERATE)).collect(toSet());
  }

  @Override
  public String getCriterionName() {
    return CriterionName.MODERATE_VULN_COUNT.name();
  }
}
