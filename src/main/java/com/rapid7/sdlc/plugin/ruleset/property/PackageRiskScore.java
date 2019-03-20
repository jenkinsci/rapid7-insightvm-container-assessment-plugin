package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.CriterionName;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * A property that is satisfied if the overall Rapid7 risk score specified
 * in the assessment report exceeds the threshold.
 */
public class PackageRiskScore extends DoublePropertyEvaluator {

  public static final String displayName = "Package risk score exceeds";

  public PackageRiskScore() {
  }

  public PackageRiskScore(double threshold) {
    super(threshold);
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public double getValue(Image image) {
    Optional<Package> pkg = image.getPackages().stream()
        .filter(p -> p.getAssessment().getVulnerabilities().getTotal() > 0)
        .filter(p -> p.getAssessment().getRiskScore() > threshold)
        .max(Comparator.comparing(p -> p.getAssessment().getRiskScore()));

    return pkg.isPresent() ? pkg.get().getAssessment().getRiskScore() : 0.0;
  }

  @Override
  public Set<Package> getQualifyingPackages(Image image) {
    return image.getPackages().stream()
        .filter(p -> p.getAssessment().getVulnerabilities().getTotal() > 0)
        .filter(p -> p.getAssessment().getRiskScore() > threshold).collect(toSet());
  }

  @Override
  public String getCriterionName() {
    return CriterionName.PACKAGE_RISK_SCORE.name();
  }
}
