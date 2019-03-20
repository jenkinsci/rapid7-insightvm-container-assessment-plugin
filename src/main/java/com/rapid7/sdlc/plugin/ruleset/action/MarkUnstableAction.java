package com.rapid7.sdlc.plugin.ruleset.action;

/**
 * An action to add a warning to the given build.
 */
public class MarkUnstableAction extends Action {

  public static final String displayName = "Mark Unstable";

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public ActionType getType() {
    return ActionType.MARK_UNSTABLE;
  }
}
