package com.rapid7.sdlc.plugin.api.client;

import feign.RetryableException;
import feign.Retryer;
import java.util.Arrays;

public class CustomRetryer implements Retryer {

  private long attempts;

  public CustomRetryer() {
    this.attempts = 1L;
  }

  @Override
  public void continueOrPropagate(RetryableException e) {
    final int maxAttempts = 3;
    final long waitIntervalInMs = 1000;

    boolean retryableStatus = 400 <= e.status() && e.status() < 500;
    if (attempts++ <= maxAttempts && retryableStatus) {
      try {
        Thread.sleep(waitIntervalInMs * (this.attempts * 5L));
      } catch (InterruptedException ignored) {
        Thread.currentThread().interrupt();
      }
    } else {
      throw e;
    }
  }

  @Override
  public Retryer clone() {
    try {
      super.clone();
    } catch (CloneNotSupportedException ignored) {
      // won't compile unless this is called...
    }
    return new CustomRetryer();
  }
}
