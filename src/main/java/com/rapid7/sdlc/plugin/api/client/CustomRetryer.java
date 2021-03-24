package com.rapid7.sdlc.plugin.api.client;

import feign.RetryableException;
import feign.Retryer;
import java.util.Arrays;

public class CustomRetryer implements Retryer {

  private int attempts;
  private final int[] statusesToRetryOn;

  public CustomRetryer(int... retryOnStatuses) {
    this.attempts = 1;
    this.statusesToRetryOn = retryOnStatuses;
  }

  @Override
  public void continueOrPropagate(RetryableException e) {
    final int maxAttempts = 3;
    final long waitIntervalInMs = 1000;

    boolean retryableStatus = Arrays.stream(statusesToRetryOn).anyMatch(status -> status == e.status());
    if (attempts++ <= maxAttempts && retryableStatus) {
      try {
        Thread.sleep(waitIntervalInMs);
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
    return new CustomRetryer(statusesToRetryOn);
  }
}
