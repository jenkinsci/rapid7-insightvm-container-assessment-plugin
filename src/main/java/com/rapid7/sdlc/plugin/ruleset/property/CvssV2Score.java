package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.CriterionName;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;
import java.util.OptionalDouble;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * A property that is satisfied if one or more of the vulnerablities in the
 * assessment report has a CVSS score exceeds the threshold.
 */
public class CvssV2Score extends DoublePropertyEvaluator {

  public static final String displayName = "Max CVSS exceeds";

  public CvssV2Score() {

  }

  public CvssV2Score(double threshold) {
    super(threshold);
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public RuleResult check(Image image) {
    return new RuleResult(image.getAssessment().getFindings().stream().anyMatch(vuln -> vuln.getVulnerability().getCvssV2().getScore() > threshold), String.valueOf(getValue(image)));
  }

  @Override
  public double getValue(Image image) {
    OptionalDouble value = image.getAssessment().getFindings().stream().mapToDouble(vuln -> vuln.getVulnerability().getCvssV2().getScore()).max();
    return value.isPresent() ? value.getAsDouble() : 0.0;
  }

  @Override
  public Set<Package> getQualifyingPackages(Image image) {
    return image.getPackages().stream().filter(pkg -> pkg.getAssessment().getFindings().stream().anyMatch(fnd -> fnd.getVulnerability().getCvssV2().getScore() > threshold)).collect(toSet());
  }

  @Override
  public String getCriterionName() {
    return CriterionName.MAX_CVSS_SCORE.name();
  }
}
