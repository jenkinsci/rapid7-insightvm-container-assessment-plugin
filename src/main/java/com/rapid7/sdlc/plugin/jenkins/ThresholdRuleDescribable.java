package com.rapid7.sdlc.plugin.jenkins;

import com.rapid7.sdlc.plugin.ruleset.action.Action;
import com.rapid7.sdlc.plugin.ruleset.property.PropertyEvaluator;
import hudson.model.AbstractDescribableImpl;

public abstract class ThresholdRuleDescribable extends AbstractDescribableImpl<ThresholdRuleDescribable> {
  public Action getActionObject() {
    return null;
  }

  public PropertyEvaluator getPropertyEvaluator() {
    return null;
  }
}
