package com.rapid7.sdlc.plugin.api.client;

import feign.RetryableException;
import feign.Retryer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomRetryer implements Retryer {

  private int attempts;
  private final List<Integer> statusesToRetryOn;

  public CustomRetryer(Integer... retryOnStatuses) {
    this.attempts = 1;
    this.statusesToRetryOn = new ArrayList<>(Arrays.asList(retryOnStatuses));
  }

  @Override
  public void continueOrPropagate(RetryableException e) {
    final int maxAttempts = 3;
    final long waitIntervalInMs = 1000;

    boolean retryableStatus = statusesToRetryOn.contains(e.status());
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
    return new CustomRetryer();
  }
}
