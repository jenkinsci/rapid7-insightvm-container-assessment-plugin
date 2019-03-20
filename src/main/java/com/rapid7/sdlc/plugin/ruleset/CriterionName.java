package com.rapid7.sdlc.plugin.ruleset;

public enum CriterionName {
  CRITICAL_VULN_COUNT,
  EXPLOITABLE_VULN_COUNT,
  MAX_CVSS_SCORE,
  MODERATE_VULN_COUNT,
  PACKAGE_NAME,
  PACKAGE_RISK_SCORE,
  RISK_SCORE,
  SEVERE_VULN_COUNT,
  TOTAL_VULN_COUNT,
  VULN_CATEGORY,
  VULN_NAME,
  VULN_PACKAGE_NAME,
  VULNS_WITH_MALWARE_COUNT;
  
  public static CriterionName fromValue(String value) {
    for (CriterionName status : values())
      if (status.name().equalsIgnoreCase(value))
        return status;

    return null;
  }
}
