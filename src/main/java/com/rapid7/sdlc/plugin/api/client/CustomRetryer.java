package com.rapid7.sdlc.plugin.api.client;

import feign.RetryableException;
import feign.Retryer;

public class CustomRetryer implements Retryer {

  private int attempts;

  public CustomRetryer() {
    this.attempts = 1;
  }

  @Override
  public void continueOrPropagate(RetryableException e) {
    final int maxAttempts = 3;
    final long waitIntervalInMs = 1000;

    // Retry on 404s
    if (attempts++ <= maxAttempts && e.status() == 404) {
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
