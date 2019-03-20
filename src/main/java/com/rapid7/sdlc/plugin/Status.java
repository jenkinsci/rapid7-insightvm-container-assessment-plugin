package com.rapid7.sdlc.plugin;

public enum Status {

  SUCCESS,
  FAILURE,
  UNSTABLE;

  public static Status fromValue(String type) {
    if (type != null)
      for (Status value : values())
        if (value.name().equalsIgnoreCase(type))
          return value;

    return null;
  }
}
