package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;

/**
 * Evaluates some arbitrary property of an assessment report, comparing the
 * result against an integer threshold.
 */
public abstract class IntegerPropertyEvaluator implements PropertyEvaluator {
  int threshold;

  public IntegerPropertyEvaluator() {
    threshold = 0;
  }

  public IntegerPropertyEvaluator(int threshold) {
    this.threshold = threshold;
  }

  public double getThreshold() {
    return threshold;
  }

  public void setThreshold(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean isValid() {
    return threshold > 0;
  }

  @Override
  public RuleResult check(Image result) {
    int value = getValue(result);
    return new RuleResult(getValue(result) > threshold, String.valueOf(value));
  }

  public abstract int getValue(Image assessment);

  @Override
  public String getConfiguredValue() {
    return String.valueOf(threshold);
  }
}
