package com.rapid7.sdlc.plugin.ruleset.action;

/**
 * An action that can modify the state of a build (fail, warn, etc).
 */
public abstract class Action {
  public enum ActionType {

    MARK_UNSTABLE,
    FAIL;

    public static ActionType fromValue(String type) {
      if (type != null)
        for (ActionType value : values())
          if (value.name().equalsIgnoreCase(type))
            return value;

      return null;
    }
  }

  /**
   * @return The name of this action as it should appear
   *         in the UI.
   */
  public abstract String getDisplayName();
  
  public abstract ActionType getType();
}
