package com.rapid7.sdlc.plugin.ruleset;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.ruleset.action.Action;
import com.rapid7.sdlc.plugin.ruleset.property.PropertyEvaluator;

/**
 * A rule specifying an arbitrary property of an assessment report,a numerical
 * threshold, and an action to be taken when the threshold is satisfied.
 */
public class Rule {

  private Action action;
  private PropertyEvaluator propertyEvaluator;

  public Rule() {
    action = null;
    propertyEvaluator = null;
  }

  /**
   * Class constructor specifying a threshold, an action, and a property.
   *
   * @param action An action to take when the threshold is
   *        satisfied.
   * @param propertyEvaluator An evaluator to check the property against
   *        the threshold.
   */
  public Rule(Action action, PropertyEvaluator propertyEvaluator) {
    this.action = action;
    this.propertyEvaluator = propertyEvaluator;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public PropertyEvaluator getPropertyEvaluator() {
    return propertyEvaluator;
  }

  public void setPropertyEvaluator(PropertyEvaluator propertyEvaluator) {
    this.propertyEvaluator = propertyEvaluator;
  }

  public boolean isValid() {
    if (propertyEvaluator != null)
      return propertyEvaluator.isValid();

    return false;
  }

  /**
   * Checks the given assessment report against the current property
   * evaluator, if any.
   *
   * @param image The assessment report to check.
   * @return <code>true</code> if the threshold satisfies
   *         the evaluator, <code>false</code> otherwise
   *         (or if no evaluator was set).
   */
  public RuleResult check(Image image) {
    if (propertyEvaluator == null)
      return null;

    return propertyEvaluator.check(image);
  }

  /**
   * Gets a string describing why the current action would be triggered.
   *
   * @return The above-described string.
   */
  public String getMessage() {
    return propertyEvaluator.getDisplayName() + " triggered " + action.getDisplayName();
  }
}
