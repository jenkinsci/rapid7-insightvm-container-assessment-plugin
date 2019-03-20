package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;
import hudson.Util;
import java.util.List;

/**
 * Evaluates some arbitrary property of an assessment report, comparing the
 * result against a numeric threshold.
 */
public abstract class StringContainsPropertyEvaluator implements PropertyEvaluator {
  String pattern;

  public StringContainsPropertyEvaluator() {
    pattern = "";
  }

  public StringContainsPropertyEvaluator(String pattern) {
    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean isValid() {
    return Util.fixEmptyAndTrim(pattern) != null;
  }

  @Override
  public String getConfiguredValue() {
    return pattern;
  }

  public abstract List<String> getMatches(Image result);

  @Override
  public RuleResult check(Image result) {
    List<String> matches = getMatches(result);
    String value = null;
    boolean match = false;
    if (!matches.isEmpty()) {
      match = true;
      if (matches.size() > 1)
        value = "Multiple matches";
      else
        value = matches.iterator().next();
    } else {
      value = "N/A";
    }
    return new RuleResult(match, value);
  }
}
