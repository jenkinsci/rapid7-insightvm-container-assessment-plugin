package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;
import java.util.Set;

/**
 * Evaluates some arbitrary property of an assessment report, comparing the
 * result against a numeric threshold.
 */
public interface PropertyEvaluator {

  /**
   * @return The name of this action as it should appear
   *         in the UI.
   */
  public String getDisplayName(); // TODO: all of these need to be i18n

  /**
   * @return The name of the criterion checked by this evaluator.
   */
  public String getCriterionName();

  /**
   * @return The threshold or matching string to perform the evaluation on.
   */
  public String getConfiguredValue();

  /**
   * @return true if the rule is valid and can be evaluated.
   */
  public boolean isValid();

  /**
   * Check the given assessment report against the given threshold.
   *
   * @param image The assessment report to check.
   * @return <code>true</code> if the report satisfies
   *         the threshold; <code>false</code> otherwise.
   */
  public RuleResult check(Image image);

  /**
   * @param image The assessment report to check.
   * @return A set of all packages that would contribute to triggering this rule.
   */
  public Set<Package> getQualifyingPackages(Image image);
}
