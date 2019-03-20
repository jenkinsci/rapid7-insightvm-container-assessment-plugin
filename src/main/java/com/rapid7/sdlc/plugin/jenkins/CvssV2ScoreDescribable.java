package com.rapid7.sdlc.plugin.jenkins;

import com.rapid7.sdlc.plugin.ruleset.action.Action;
import com.rapid7.sdlc.plugin.ruleset.action.FailAction;
import com.rapid7.sdlc.plugin.ruleset.action.MarkUnstableAction;
import com.rapid7.sdlc.plugin.ruleset.property.CvssV2Score;
import com.rapid7.sdlc.plugin.ruleset.property.PropertyEvaluator;
import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.Item;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import javax.annotation.Nonnull;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.verb.POST;

public class CvssV2ScoreDescribable extends ThresholdRuleDescribable {
  private final String action;
  private final String threshold;
  private final CvssV2Score rule;

  @DataBoundConstructor
  public CvssV2ScoreDescribable(String action, String threshold) {
    this.action = action;
    this.threshold = threshold;
    this.rule = new CvssV2Score(Integer.parseInt(threshold));
  }

  public String getAction() {
    return action;
  }

  public String getThreshold() {
    return threshold;
  }

  @Override
  public PropertyEvaluator getPropertyEvaluator() {
    return rule;
  }

  @Override
  public Action getActionObject() {
    if ("Mark Unstable".equalsIgnoreCase(action))
      return new MarkUnstableAction();
    else if ("Fail".equalsIgnoreCase(action))
      return new FailAction();
    else
      return null;
  }

  @Extension
  @Symbol("cvssV2Score")
  public static class DescriptorImpl extends Descriptor<ThresholdRuleDescribable> {
    @Nonnull
    @Override
    public String getDisplayName() {
      return Messages.ContainerAssessmentBuilder_CvssV2ScoreRule();
    }

    public ListBoxModel doFillActionItems() {
      return new ListBoxModel().add("Fail").add("Mark Unstable");
    }

    @POST
    public FormValidation doCheckThreshold(@QueryParameter String value, @AncestorInPath Item item) {
      if (item == null) {
        return FormValidation.ok();
      }
      item.checkPermission(Item.CONFIGURE);

      try {
        double parsedValue = Double.parseDouble(value);
        if (parsedValue < 0.0)
          return FormValidation.error(Messages.ContainerAssessmentBuilder_ThresholdRuleValidationPositiveNumber());
        else if (parsedValue > 9.9) {
          return FormValidation.error(Messages.CvssV2ScoreDescribable_ThresholdRuleValidationMaxCVSS());
        }
      } catch (NumberFormatException nfe) {
        return FormValidation.error(Messages.ContainerAssessmentBuilder_ThresholdRuleValidationNumberRequired());
      }

      return FormValidation.ok();
    }
  }
}

