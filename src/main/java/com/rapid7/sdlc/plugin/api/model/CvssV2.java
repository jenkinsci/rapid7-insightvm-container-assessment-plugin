package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.StringJoiner;

public class CvssV2 {

  /**
   * Gets or Sets accessComplexity
   */
  public enum AccessComplexityEnum {
    L("L"),
    
    M("M"),
    
    H("H");

    private String value;

    AccessComplexityEnum(String value) {
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
    public static AccessComplexityEnum fromValue(String text) {
      for (AccessComplexityEnum b : AccessComplexityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("access_complexity")
  private AccessComplexityEnum accessComplexity = null;

  /**
   * Gets or Sets accessVector
   */
  public enum AccessVectorEnum {
    L("L"),
    
    A("A"),
    
    N("N");

    private String value;

    AccessVectorEnum(String value) {
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
    public static AccessVectorEnum fromValue(String text) {
      for (AccessVectorEnum b : AccessVectorEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("access_vector")
  private AccessVectorEnum accessVector = null;

  /**
   * Gets or Sets authentication
   */
  public enum AuthenticationEnum {
    N("N"),
    
    S("S"),
    
    M("M");

    private String value;

    AuthenticationEnum(String value) {
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
    public static AuthenticationEnum fromValue(String text) {
      for (AuthenticationEnum b : AuthenticationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("authentication")
  private AuthenticationEnum authentication = null;

  /**
   * Gets or Sets availabilityImpact
   */
  public enum AvailabilityImpactEnum {
    N("N"),
    
    P("P"),
    
    C("C");

    private String value;

    AvailabilityImpactEnum(String value) {
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
    public static AvailabilityImpactEnum fromValue(String text) {
      for (AvailabilityImpactEnum b : AvailabilityImpactEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("availability_impact")
  private AvailabilityImpactEnum availabilityImpact = null;

  /**
   * Gets or Sets confidentialityImpact
   */
  public enum ConfidentialityImpactEnum {
    N("N"),
    
    P("P"),
    
    C("C");

    private String value;

    ConfidentialityImpactEnum(String value) {
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
    public static ConfidentialityImpactEnum fromValue(String text) {
      for (ConfidentialityImpactEnum b : ConfidentialityImpactEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("confidentiality_impact")
  private ConfidentialityImpactEnum confidentialityImpact = null;

  @JsonProperty("exploit_score")
  private Float exploitScore = null;

  @JsonProperty("impact_score")
  private Float impactScore = null;

  /**
   * Gets or Sets integrityImpact
   */
  public enum IntegrityImpactEnum {
    N("N"),
    
    P("P"),
    
    C("C");

    private String value;

    IntegrityImpactEnum(String value) {
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
    public static IntegrityImpactEnum fromValue(String text) {
      for (IntegrityImpactEnum b : IntegrityImpactEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("integrity_impact")
  private IntegrityImpactEnum integrityImpact = null;

  @JsonProperty("score")
  private Double score = null;

  @JsonProperty("vector")
  private String vector = null;

  public CvssV2 accessComplexity(AccessComplexityEnum accessComplexity) {
    this.accessComplexity = accessComplexity;
    return this;
  }

  public AccessComplexityEnum getAccessComplexity() {
    return accessComplexity;
  }

  public void setAccessComplexity(AccessComplexityEnum accessComplexity) {
    this.accessComplexity = accessComplexity;
  }

  public CvssV2 accessVector(AccessVectorEnum accessVector) {
    this.accessVector = accessVector;
    return this;
  }

  public AccessVectorEnum getAccessVector() {
    return accessVector;
  }

  public void setAccessVector(AccessVectorEnum accessVector) {
    this.accessVector = accessVector;
  }

  public CvssV2 authentication(AuthenticationEnum authentication) {
    this.authentication = authentication;
    return this;
  }

  public AuthenticationEnum getAuthentication() {
    return authentication;
  }

  public void setAuthentication(AuthenticationEnum authentication) {
    this.authentication = authentication;
  }

  public CvssV2 availabilityImpact(AvailabilityImpactEnum availabilityImpact) {
    this.availabilityImpact = availabilityImpact;
    return this;
  }

  public AvailabilityImpactEnum getAvailabilityImpact() {
    return availabilityImpact;
  }

  public void setAvailabilityImpact(AvailabilityImpactEnum availabilityImpact) {
    this.availabilityImpact = availabilityImpact;
  }

  public CvssV2 confidentialityImpact(ConfidentialityImpactEnum confidentialityImpact) {
    this.confidentialityImpact = confidentialityImpact;
    return this;
  }

  public ConfidentialityImpactEnum getConfidentialityImpact() {
    return confidentialityImpact;
  }

  public void setConfidentialityImpact(ConfidentialityImpactEnum confidentialityImpact) {
    this.confidentialityImpact = confidentialityImpact;
  }

  public CvssV2 exploitScore(Float exploitScore) {
    this.exploitScore = exploitScore;
    return this;
  }

  public Float getExploitScore() {
    return exploitScore;
  }

  public void setExploitScore(Float exploitScore) {
    this.exploitScore = exploitScore;
  }

  public CvssV2 impactScore(Float impactScore) {
    this.impactScore = impactScore;
    return this;
  }

  public Float getImpactScore() {
    return impactScore;
  }

  public void setImpactScore(Float impactScore) {
    this.impactScore = impactScore;
  }

  public CvssV2 integrityImpact(IntegrityImpactEnum integrityImpact) {
    this.integrityImpact = integrityImpact;
    return this;
  }

  public IntegrityImpactEnum getIntegrityImpact() {
    return integrityImpact;
  }

  public void setIntegrityImpact(IntegrityImpactEnum integrityImpact) {
    this.integrityImpact = integrityImpact;
  }

  public CvssV2 score(Double score) {
    this.score = score;
    return this;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public CvssV2 vector(String vector) {
    this.vector = vector;
    return this;
  }

  public String getVector() {
    return vector;
  }

  public void setVector(String vector) {
    this.vector = vector;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof CvssV2))
      return false;
    else {
      CvssV2 cvssV2 = (CvssV2) obj;
      return Objects.equals(this.accessComplexity, cvssV2.accessComplexity)
          && Objects.equals(this.accessVector, cvssV2.accessVector)
          && Objects.equals(this.authentication, cvssV2.authentication)
          && Objects.equals(this.availabilityImpact, cvssV2.availabilityImpact)
          && Objects.equals(this.confidentialityImpact, cvssV2.confidentialityImpact)
          && Objects.equals(this.exploitScore, cvssV2.exploitScore)
          && Objects.equals(this.impactScore, cvssV2.impactScore)
          && Objects.equals(this.integrityImpact, cvssV2.integrityImpact)
          && Objects.equals(this.score, cvssV2.score)
          && Objects.equals(this.vector, cvssV2.vector);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessComplexity, accessVector, authentication, availabilityImpact, confidentialityImpact, exploitScore, impactScore, integrityImpact, score, vector);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CvssV2.class.getSimpleName() + "[", "]")
        .add("accessComplexity=" + accessComplexity)
        .add("accessVector=" + accessVector)
        .add("authentication=" + authentication)
        .add("availabilityImpact=" + availabilityImpact)
        .add("confidentialityImpact=" + confidentialityImpact)
        .add("exploitScore=" + exploitScore)
        .add("impactScore=" + impactScore)
        .add("integrityImpact=" + integrityImpact)
        .add("score=" + score)
        .add("vector=" + vector)
        .toString();
  }
}
