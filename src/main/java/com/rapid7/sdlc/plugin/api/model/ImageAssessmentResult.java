package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.StringJoiner;

public class ImageAssessmentResult {

  @JsonProperty("check_id")
  private String checkId = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("proof")
  private String proof = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    UNKNOWN("unknown"),
    
    NOT_VULNERABLE("not-vulnerable"),
    
    VULNERABLE("vulnerable"),
    
    VULNERABLE_VERSION("vulnerable-version"),
    
    VULNERABLE_POTENTIAL("vulnerable-potential"),
    
    VULNERABLE_WITH_EXCEPTION_APPLIED("vulnerable-with-exception-applied"),
    
    VULNERABLE_VERSION_WITH_EXCEPTION_APPLIED("vulnerable-version-with-exception-applied"),
    
    VULNERABLE_POTENTIAL_WITH_EXCEPTION_APPLIED("vulnerable-potential-with-exception-applied");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("vulnerability_id")
  private String vulnerabilityId = null;

  public ImageAssessmentResult checkId(String checkId) {
    this.checkId = checkId;
    return this;
  }

  public String getCheckId() {
    return checkId;
  }

  public void setCheckId(String checkId) {
    this.checkId = checkId;
  }

  public ImageAssessmentResult key(String key) {
    this.key = key;
    return this;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public ImageAssessmentResult proof(String proof) {
    this.proof = proof;
    return this;
  }

  public String getProof() {
    return proof;
  }

  public void setProof(String proof) {
    this.proof = proof;
  }

  public ImageAssessmentResult status(StatusEnum status) {
    this.status = status;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ImageAssessmentResult vulnerabilityId(String vulnerabilityId) {
    this.vulnerabilityId = vulnerabilityId;
    return this;
  }

  public String getVulnerabilityId() {
    return vulnerabilityId;
  }

  public void setVulnerabilityId(String vulnerabilityId) {
    this.vulnerabilityId = vulnerabilityId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof ImageAssessmentResult))
      return false;
    else {
      ImageAssessmentResult imageAssessmentResult = (ImageAssessmentResult) obj;
      return Objects.equals(this.checkId, imageAssessmentResult.checkId)
          && Objects.equals(this.key, imageAssessmentResult.key)
          && Objects.equals(this.proof, imageAssessmentResult.proof)
          && Objects.equals(this.status, imageAssessmentResult.status)
          && Objects.equals(this.vulnerabilityId, imageAssessmentResult.vulnerabilityId);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(checkId, key, proof, status, vulnerabilityId);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ImageAssessmentResult.class.getSimpleName() + "[", "]")
        .add("checkId=" + checkId)
        .add("key=" + key)
        .add("proof=" + proof)
        .add("status=" + status)
        .add("vulnerabilityId=" + vulnerabilityId)
        .toString();
  }
}
