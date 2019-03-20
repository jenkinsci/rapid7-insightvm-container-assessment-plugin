package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Rule {

  public enum ActionEnum {
    MARK_UNSTABLE("MARK_UNSTABLE"),
    
    FAIL("FAIL");

    private String value;

    ActionEnum(String value) {
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
    public static ActionEnum fromValue(String text) {
      for (ActionEnum b : ActionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("action")
  private ActionEnum action = null;

  @JsonProperty("actual_value")
  private String actualValue = null;

  @JsonProperty("configured_value")
  private String configuredValue = null;

  public enum CriterionEnum {
    CRITICAL_VULN_COUNT("CRITICAL_VULN_COUNT"),
    
    EXPLOITABLE_VULN_COUNT("EXPLOITABLE_VULN_COUNT"),
    
    MAX_CVSS_SCORE("MAX_CVSS_SCORE"),
    
    MODERATE_VULN_COUNT("MODERATE_VULN_COUNT"),
    
    PACKAGE_NAME("PACKAGE_NAME"),
    
    PACKAGE_RISK_SCORE("PACKAGE_RISK_SCORE"),
    
    RISK_SCORE("RISK_SCORE"),
    
    SEVERE_VULN_COUNT("SEVERE_VULN_COUNT"),
    
    TOTAL_VULN_COUNT("TOTAL_VULN_COUNT"),
    
    VULN_CATEGORY("VULN_CATEGORY"),
    
    VULN_NAME("VULN_NAME"),
    
    VULN_PACKAGE_NAME("VULN_PACKAGE_NAME"),
    
    VULNS_WITH_MALWARE_COUNT("VULNS_WITH_MALWARE_COUNT");

    private String value;

    CriterionEnum(String value) {
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
    public static CriterionEnum fromValue(String text) {
      for (CriterionEnum b : CriterionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("criterion")
  private CriterionEnum criterion = null;

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("package_ids")
  private List<Long> packageIds = new ArrayList<>();

  public enum StatusEnum {
    FAILED("FAILED"),
    
    PASSED("PASSED");

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

  @JsonProperty("vulnerability_ids")
  private List<String> vulnerabilityIds = new ArrayList<>();

  public Rule action(ActionEnum action) {
    this.action = action;
    return this;
  }

  public ActionEnum getAction() {
    return action;
  }

  public void setAction(ActionEnum action) {
    this.action = action;
  }

  public Rule actualValue(String actualValue) {
    this.actualValue = actualValue;
    return this;
  }

  public String getActualValue() {
    return actualValue;
  }

  public void setActualValue(String actualValue) {
    this.actualValue = actualValue;
  }

  public Rule configuredValue(String configuredValue) {
    this.configuredValue = configuredValue;
    return this;
  }

  public String getConfiguredValue() {
    return configuredValue;
  }

  public void setConfiguredValue(String configuredValue) {
    this.configuredValue = configuredValue;
  }

  public Rule criterion(CriterionEnum criterion) {
    this.criterion = criterion;
    return this;
  }

  public CriterionEnum getCriterion() {
    return criterion;
  }

  public void setCriterion(CriterionEnum criterion) {
    this.criterion = criterion;
  }

  public Rule links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Rule addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public Rule packageIds(List<Long> packageIds) {
    this.packageIds = packageIds;
    return this;
  }

  public Rule addPackageIdsItem(Long packageIdsItem) {
    this.packageIds.add(packageIdsItem);
    return this;
  }

  public List<Long> getPackageIds() {
    return packageIds;
  }

  public void setPackageIds(List<Long> packageIds) {
    this.packageIds = packageIds;
  }

  public Rule status(StatusEnum status) {
    this.status = status;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Rule vulnerabilityIds(List<String> vulnerabilityIds) {
    this.vulnerabilityIds = vulnerabilityIds;
    return this;
  }

  public Rule addVulnerabilityIdsItem(String vulnerabilityIdsItem) {
    this.vulnerabilityIds.add(vulnerabilityIdsItem);
    return this;
  }

  public List<String> getVulnerabilityIds() {
    return vulnerabilityIds;
  }

  public void setVulnerabilityIds(List<String> vulnerabilityIds) {
    this.vulnerabilityIds = vulnerabilityIds;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Rule))
      return false;
    else {
      Rule rule = (Rule) obj;
      return Objects.equals(this.action, rule.action)
          && Objects.equals(this.actualValue, rule.actualValue)
          && Objects.equals(this.configuredValue, rule.configuredValue)
          && Objects.equals(this.criterion, rule.criterion)
          && Objects.equals(this.links, rule.links)
          && Objects.equals(this.packageIds, rule.packageIds)
          && Objects.equals(this.status, rule.status)
          && Objects.equals(this.vulnerabilityIds, rule.vulnerabilityIds);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, actualValue, configuredValue, criterion, links, packageIds, status, vulnerabilityIds);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Rule.class.getSimpleName() + "[", "]")
        .add("action=" + action)
        .add("actualValue=" + actualValue)
        .add("configuredValue=" + configuredValue)
        .add("criterion=" + criterion)
        .add("links=" + links)
        .add("packageIds=" + packageIds)
        .add("status=" + status)
        .add("vulnerabilityIds=" + vulnerabilityIds)
        .toString();
  }
}
