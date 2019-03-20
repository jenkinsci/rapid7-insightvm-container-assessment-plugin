package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.CriterionName;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * A property that is satisfied if the total number vulnerabilities
 * in the assessment report exceeds the threshold.
 */
public class TotalVulnerabilities extends IntegerPropertyEvaluator {

  public static final String displayName = "Total vulnerability count exceeds";

  public TotalVulnerabilities() {

  }

  public TotalVulnerabilities(int threshold) {
    super(threshold);
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public int getValue(Image image) {
    return image.getAssessment().getVulnerabilities().getTotal();
  }

  @Override
  public Set<Package> getQualifyingPackages(Image image) {
    return image.getPackages().stream().filter(pkg -> pkg.getAssessment().getVulnerabilities().getTotal() > 0).collect(toSet());
  }

  @Override
  public String getCriterionName() {
    return CriterionName.TOTAL_VULN_COUNT.name();
  }
}
