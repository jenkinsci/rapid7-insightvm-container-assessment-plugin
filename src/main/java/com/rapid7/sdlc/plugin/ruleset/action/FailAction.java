package com.rapid7.sdlc.plugin.ruleset.action;

/**
 * An action to fail the given build.
 */
public class FailAction extends Action {
  
  public static final String displayName = "Fail";

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public ActionType getType() {
    return ActionType.FAIL;
  }
}
