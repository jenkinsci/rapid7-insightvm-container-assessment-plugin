package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.StringJoiner;

public class CvssV3 {

  /**
   * Access Complexity (AC) component with measures the conditions beyond the attacker&#39;s control that must exist in order to exploit the vulnerability.  | Access Complexity      | Description                                                              |  | ---------------------- | ------------------------------------------------------------------------ |  | Low (&#x60;\&quot;L\&quot;&#x60;)            | Specialized access conditions or extenuating circumstances do not exist. |  | High (&#x60;\&quot;H\&quot;&#x60;)           | A successful attack depends on conditions beyond the attacker&#39;s control. |
   */
  public enum AttackComplexityEnum {
    L("L"),
    
    H("H");

    private String value;

    AttackComplexityEnum(String value) {
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
    public static AttackComplexityEnum fromValue(String text) {
      for (AttackComplexityEnum b : AttackComplexityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("attack_complexity")
  private AttackComplexityEnum attackComplexity = null;

  /**
   * Attack Vector (AV) component which measures context by which vulnerability exploitation is possible.  | Access Vector          | Description                                                              |  | ---------------------- | ------------------------------------------------------------------------ |  | Local (&#x60;\&quot;L\&quot;&#x60;)          | A vulnerability exploitable with only local access requires the attacker to have either physical access to the vulnerable system or a local (shell) account. |  | Adjacent (&#x60;\&quot;A\&quot;&#x60;)       | A vulnerability exploitable with adjacent network access requires the attacker to have access to either the broadcast or collision domain of the vulnerable software. |  | Network (&#x60;\&quot;N\&quot;&#x60;)        | A vulnerability exploitable with network access means the vulnerable software is bound to the network stack and the attacker does not require local network access or local access. Such a vulnerability is often termed \&quot;remotely exploitable\&quot;. |  
   */
  public enum AttackVectorEnum {
    N("N"),
    
    A("A"),
    
    L("L"),
    
    P("P");

    private String value;

    AttackVectorEnum(String value) {
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
    public static AttackVectorEnum fromValue(String text) {
      for (AttackVectorEnum b : AttackVectorEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("attack_vector")
  private AttackVectorEnum attackVector = null;

  /**
   * Integrity Impact (I) measures the impact to integrity of a successfully exploited vulnerability. Integrity refers to the trustworthiness and veracity of information.  | Integrity Impact    | Description  |  | ------------------- | ------------ |  | High (&#x60;\&quot;H\&quot;&#x60;)        | There is a total loss of integrity, or a complete loss of protection. |  | Low (&#x60;\&quot;L\&quot;&#x60;)         | Modification of data is possible, but the attacker does not have control over the consequence of a modification, or the amount of modification is constrained. |  | None (&#x60;\&quot;N\&quot;&#x60;)        | There is no loss of integrity within the impacted component. |
   */
  public enum AvailabilityImpactEnum {
    N("N"),
    
    L("L"),
    
    H("H");

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
   * Confidentiality Impact (C) component which measures the impact on confidentiality of a successfully exploited vulnerability.  | Confidentiality Impact     | Description  |  | -------------------------- | ------------ |  | High (&#x60;\&quot;H\&quot;&#x60;)               | There is total loss of confidentiality, resulting in all resources within the impacted component being divulged to the attacker. |  | Low (&#x60;\&quot;L\&quot;&#x60;)                | There is some loss of confidentiality. Access to some restricted information is obtained, but the attacker does not have control over what information is obtained, or the amount or kind of loss is constrained. |  | None (&#x60;\&quot;N\&quot;&#x60;)               | There is no loss of confidentiality within the impacted component. |
   */
  public enum ConfidentialityImpactEnum {
    N("N"),
    
    L("L"),
    
    H("H");

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
   * Integrity Impact (I) measures the impact to integrity of a successfully exploited vulnerability. Integrity refers to the trustworthiness and veracity of information.  | Integrity Impact    | Description  |  | ------------------- | ------------ |  | High (&#x60;\&quot;H\&quot;&#x60;)        | There is a total loss of integrity, or a complete loss of protection. |  | Low (&#x60;\&quot;L\&quot;&#x60;)         | Modification of data is possible, but the attacker does not have control over the consequence of a modification, or the amount of modification is constrained. |  | None (&#x60;\&quot;N\&quot;&#x60;)        | There is no loss of integrity within the impacted component. |
   */
  public enum IntegrityImpactEnum {
    N("N"),
    
    L("L"),
    
    H("H");

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

  /**
   * Privileges Required (PR) measures the level of privileges an attacker must possess before successfully exploiting the vulnerability.  | Privileges Required (PR)     | Description                                                              |  | ---------------------------- | ------------------------------------------------------------------------ |  | None (&#x60;\&quot;N\&quot;&#x60;)                 | The attacker is unauthorized prior to attack, and therefore does not require any access to settings or files to carry out an attack. |  | Low (&#x60;\&quot;L\&quot;&#x60;)                  | The attacker is authorized with (i.e. requires) privileges that provide basic user capabilities that could normally affect only settings and files owned by a user. |  | High (&#x60;\&quot;H\&quot;&#x60;)                 | The attacker is authorized with (i.e. requires) privileges that provide significant (e.g. administrative) control over the vulnerable component that could affect component-wide settings and files. |
   */
  public enum PrivilegesRequiredEnum {
    N("N"),
    
    L("L"),
    
    H("H");

    private String value;

    PrivilegesRequiredEnum(String value) {
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
    public static PrivilegesRequiredEnum fromValue(String text) {
      for (PrivilegesRequiredEnum b : PrivilegesRequiredEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("privileges_required")
  private PrivilegesRequiredEnum privilegesRequired = null;

  /**
   * Scope (S) measures the collection of privileges defined by a computing authority (e.g. an application, an operating system, or a sandbox environment) when granting access to computing resources (e.g. files, CPU, memory, etc). These privileges are assigned based on some method of identification and authorization.  | Scope (S)            | Description                                                              |  | -------------------- | ------------------------------------------------------------------------ |  | Unchanged (&#x60;\&quot;U\&quot;&#x60;)    | An exploited vulnerability can only affect resources managed by the same authority. In this case the vulnerable component and the impacted component are the same. |  | Changed (&#x60;\&quot;C\&quot;&#x60;)      | An exploited vulnerability can affect resources beyond the authorization privileges intended by the vulnerable component. In this case the vulnerable component and the impacted component are different. |
   */
  public enum ScopeEnum {
    U("U"),
    
    C("C");

    private String value;

    ScopeEnum(String value) {
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
    public static ScopeEnum fromValue(String text) {
      for (ScopeEnum b : ScopeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("scope")
  private ScopeEnum scope = null;

  @JsonProperty("score")
  private Double score = null;

  /**
   * User Interaction (UI) measures the requirement for a user, other than the attacker, to participate in the successful compromise of the vulnerable component.  | User Interaction (UI)        | Description                                                               |  | ---------------------------- | ------------------------------------------------------------------------- |  | None (&#x60;\&quot;N\&quot;&#x60;)                 | The vulnerable system can be exploited without interaction from any user. |  | Required (&#x60;\&quot;R\&quot;&#x60;)             | Successful exploitation of this vulnerability requires a user to take some action before the vulnerability can be exploited. |
   */
  public enum UserInteractionEnum {
    N("N"),
    
    R("R");

    private String value;

    UserInteractionEnum(String value) {
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
    public static UserInteractionEnum fromValue(String text) {
      for (UserInteractionEnum b : UserInteractionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("user_interaction")
  private UserInteractionEnum userInteraction = null;

  @JsonProperty("vector")
  private String vector = null;

  public CvssV3 attackComplexity(AttackComplexityEnum attackComplexity) {
    this.attackComplexity = attackComplexity;
    return this;
  }

  public AttackComplexityEnum getAttackComplexity() {
    return attackComplexity;
  }

  public void setAttackComplexity(AttackComplexityEnum attackComplexity) {
    this.attackComplexity = attackComplexity;
  }

  public CvssV3 attackVector(AttackVectorEnum attackVector) {
    this.attackVector = attackVector;
    return this;
  }

  public AttackVectorEnum getAttackVector() {
    return attackVector;
  }

  public void setAttackVector(AttackVectorEnum attackVector) {
    this.attackVector = attackVector;
  }

  public CvssV3 availabilityImpact(AvailabilityImpactEnum availabilityImpact) {
    this.availabilityImpact = availabilityImpact;
    return this;
  }

  public AvailabilityImpactEnum getAvailabilityImpact() {
    return availabilityImpact;
  }

  public void setAvailabilityImpact(AvailabilityImpactEnum availabilityImpact) {
    this.availabilityImpact = availabilityImpact;
  }

  public CvssV3 confidentialityImpact(ConfidentialityImpactEnum confidentialityImpact) {
    this.confidentialityImpact = confidentialityImpact;
    return this;
  }

  public ConfidentialityImpactEnum getConfidentialityImpact() {
    return confidentialityImpact;
  }

  public void setConfidentialityImpact(ConfidentialityImpactEnum confidentialityImpact) {
    this.confidentialityImpact = confidentialityImpact;
  }

  public CvssV3 exploitScore(Float exploitScore) {
    this.exploitScore = exploitScore;
    return this;
  }

  public Float getExploitScore() {
    return exploitScore;
  }

  public void setExploitScore(Float exploitScore) {
    this.exploitScore = exploitScore;
  }

  public CvssV3 impactScore(Float impactScore) {
    this.impactScore = impactScore;
    return this;
  }

  public Float getImpactScore() {
    return impactScore;
  }

  public void setImpactScore(Float impactScore) {
    this.impactScore = impactScore;
  }

  public CvssV3 integrityImpact(IntegrityImpactEnum integrityImpact) {
    this.integrityImpact = integrityImpact;
    return this;
  }

  public IntegrityImpactEnum getIntegrityImpact() {
    return integrityImpact;
  }

  public void setIntegrityImpact(IntegrityImpactEnum integrityImpact) {
    this.integrityImpact = integrityImpact;
  }

  public CvssV3 privilegesRequired(PrivilegesRequiredEnum privilegesRequired) {
    this.privilegesRequired = privilegesRequired;
    return this;
  }

  public PrivilegesRequiredEnum getPrivilegesRequired() {
    return privilegesRequired;
  }

  public void setPrivilegesRequired(PrivilegesRequiredEnum privilegesRequired) {
    this.privilegesRequired = privilegesRequired;
  }

  public CvssV3 scope(ScopeEnum scope) {
    this.scope = scope;
    return this;
  }

  public ScopeEnum getScope() {
    return scope;
  }

  public void setScope(ScopeEnum scope) {
    this.scope = scope;
  }

  public CvssV3 score(Double score) {
    this.score = score;
    return this;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public CvssV3 userInteraction(UserInteractionEnum userInteraction) {
    this.userInteraction = userInteraction;
    return this;
  }

  public UserInteractionEnum getUserInteraction() {
    return userInteraction;
  }

  public void setUserInteraction(UserInteractionEnum userInteraction) {
    this.userInteraction = userInteraction;
  }

  public CvssV3 vector(String vector) {
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
    else if (!(obj instanceof CvssV3))
      return false;
    else {
      CvssV3 cvssV3 = (CvssV3) obj;
      return Objects.equals(this.attackComplexity, cvssV3.attackComplexity)
          && Objects.equals(this.attackVector, cvssV3.attackVector)
          && Objects.equals(this.availabilityImpact, cvssV3.availabilityImpact)
          && Objects.equals(this.confidentialityImpact, cvssV3.confidentialityImpact)
          && Objects.equals(this.exploitScore, cvssV3.exploitScore)
          && Objects.equals(this.impactScore, cvssV3.impactScore)
          && Objects.equals(this.integrityImpact, cvssV3.integrityImpact)
          && Objects.equals(this.privilegesRequired, cvssV3.privilegesRequired)
          && Objects.equals(this.scope, cvssV3.scope)
          && Objects.equals(this.score, cvssV3.score)
          && Objects.equals(this.userInteraction, cvssV3.userInteraction)
          && Objects.equals(this.vector, cvssV3.vector);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(attackComplexity, attackVector, availabilityImpact, confidentialityImpact, exploitScore, impactScore, integrityImpact, privilegesRequired, scope, score, userInteraction, vector);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CvssV3.class.getSimpleName() + "[", "]")
        .add("attackComplexity=" + attackComplexity)
        .add("attackVector=" + attackVector)
        .add("availabilityImpact=" + availabilityImpact)
        .add("confidentialityImpact=" + confidentialityImpact)
        .add("exploitScore=" + exploitScore)
        .add("impactScore=" + impactScore)
        .add("integrityImpact=" + integrityImpact)
        .add("privilegesRequired=" + privilegesRequired)
        .add("scope=" + scope)
        .add("score=" + score)
        .add("userInteraction=" + userInteraction)
        .add("vector=" + vector)
        .toString();
  }
}
