package com.rapid7.sdlc.plugin.jenkins;

import com.rapid7.sdlc.plugin.ruleset.action.Action;
import com.rapid7.sdlc.plugin.ruleset.action.FailAction;
import com.rapid7.sdlc.plugin.ruleset.action.MarkUnstableAction;
import com.rapid7.sdlc.plugin.ruleset.property.ModerateVulnerabilities;
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

public class ModerateVulnerabilitiesDescribable extends ThresholdRuleDescribable {
  private final String action;
  private final String threshold;
  private final ModerateVulnerabilities rule;

  @DataBoundConstructor
  public ModerateVulnerabilitiesDescribable(String action, String threshold) {
    this.action = action;
    this.threshold = threshold;
    this.rule = new ModerateVulnerabilities(Integer.parseInt(threshold));
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
  @Symbol("moderateVulnerabilities")
  public static class DescriptorImpl extends Descriptor<ThresholdRuleDescribable> {
    @Nonnull
    @Override
    public String getDisplayName() {
      return Messages.ContainerAssessmentBuilder_ModerateVulnerabilitiesRule();
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
        int parsedValue = Integer.parseInt(value);
        if (parsedValue < 0)
          return FormValidation.error(Messages.ContainerAssessmentBuilder_ThresholdRuleValidationPositiveNumber());
      } catch (NumberFormatException nfe) {
        return FormValidation.error(Messages.ContainerAssessmentBuilder_ThresholdRuleValidationIntegerRequired());
      }

      return FormValidation.ok();
    }
  }
}

