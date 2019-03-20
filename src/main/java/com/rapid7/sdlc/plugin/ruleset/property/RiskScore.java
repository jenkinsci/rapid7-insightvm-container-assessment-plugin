package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.CriterionName;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * A property that is satisfied if the overall Rapid7 risk score specified
 * in the assessment report exceeds the threshold.
 */
public class RiskScore extends DoublePropertyEvaluator {

  public static final String displayName = "Image risk score exceeds";

  public RiskScore() {
  }

  public RiskScore(double threshold) {
    super(threshold);
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public double getValue(Image image) {
    return image.getAssessment().getRiskScore();
  }

  @Override
  public Set<Package> getQualifyingPackages(Image image) {
    return image.getPackages().stream().filter(pkg -> pkg.getAssessment().getRiskScore() > 0.0).collect(toSet());
  }

  @Override
  public String getCriterionName() {
    return CriterionName.RISK_SCORE.name();
  }
}
