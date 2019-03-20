package com.rapid7.sdlc.plugin.ruleset;

import java.util.HashSet;
import java.util.Set;

public class RuleResult {
  
  private boolean failed;
  private String actualValue;
  private Set<Long> packageIds;
  private Set<String> vulnerabilityIds;
  
  public RuleResult(boolean failed, String actualValue) {
    this.failed = failed;
    this.actualValue = actualValue;
    this.packageIds = new HashSet<>();
    this.vulnerabilityIds = new HashSet<>();
  }

  public RuleResult(boolean failed, String actualValue, Set<Long> packageIds, Set<String> vulnerabilityIds) {
    this.failed = failed;
    this.actualValue = actualValue;
    this.packageIds = packageIds;
    this.vulnerabilityIds = vulnerabilityIds;
  }

  public boolean failed() {
    return failed;
  }

  public String getActualValue() {
    return actualValue;
  }

  public Set<Long> getPackageIds() {
    return packageIds;
  }

  public Set<String> getVulnerabilityIds() {
    return vulnerabilityIds;
  }

  public void addPackageId(Long packageId) {
    packageIds.add(packageId);
  }

  public void addVulnerabilityId(String vulnerabilityId) {
    vulnerabilityIds.add(vulnerabilityId);
  }

  public void addVulnerabilityIds(Set<String> vulnerabilityId) {
    vulnerabilityIds.addAll(vulnerabilityId);
  }
}
