package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;

/**
 * Evaluates some arbitrary property of an assessment report, comparing the
 * result against a double-precision threshold.
 */
public abstract class DoublePropertyEvaluator implements PropertyEvaluator {

  double threshold;

  public DoublePropertyEvaluator() {
    threshold = 0.0;
  }

  public DoublePropertyEvaluator(double threshold) {
    this.threshold = threshold;
  }

  public double getThreshold() {
    return threshold;
  }

  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean isValid() {
    return threshold > 0.0;
  }

  @Override
  public RuleResult check(Image result) {
    double value = getValue(result);
    return new RuleResult(value > threshold, String.valueOf(value));
  }

  public abstract double getValue(Image assessment);

  @Override
  public String getConfiguredValue() {
    return String.valueOf(threshold);
  }
}
