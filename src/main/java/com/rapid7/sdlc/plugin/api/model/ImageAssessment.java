package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ImageAssessment {

  @JsonProperty("exploitability")
  private ImageVulnerabilityExploitability exploitability = null;

  @JsonProperty("findings")
  private List<ImageVulnerabilityFinding> findings = new ArrayList<>();

  @JsonProperty("risk_score")
  private Double riskScore = null;

  @JsonProperty("vulnerabilities")
  private ImageVulnerabilityCount vulnerabilities = null;

  public ImageAssessment exploitability(ImageVulnerabilityExploitability exploitability) {
    this.exploitability = exploitability;
    return this;
  }

  public ImageVulnerabilityExploitability getExploitability() {
    return exploitability;
  }

  public void setExploitability(ImageVulnerabilityExploitability exploitability) {
    this.exploitability = exploitability;
  }

  public ImageAssessment findings(List<ImageVulnerabilityFinding> findings) {
    this.findings = findings;
    return this;
  }

  public ImageAssessment addFindingsItem(ImageVulnerabilityFinding findingsItem) {
    this.findings.add(findingsItem);
    return this;
  }

  public List<ImageVulnerabilityFinding> getFindings() {
    return findings;
  }

  public void setFindings(List<ImageVulnerabilityFinding> findings) {
    this.findings = findings;
  }

  public ImageAssessment riskScore(Double riskScore) {
    this.riskScore = riskScore;
    return this;
  }

  public Double getRiskScore() {
    return riskScore;
  }

  public void setRiskScore(Double riskScore) {
    this.riskScore = riskScore;
  }

  public ImageAssessment vulnerabilities(ImageVulnerabilityCount vulnerabilities) {
    this.vulnerabilities = vulnerabilities;
    return this;
  }

  public ImageVulnerabilityCount getVulnerabilities() {
    return vulnerabilities;
  }

  public void setVulnerabilities(ImageVulnerabilityCount vulnerabilities) {
    this.vulnerabilities = vulnerabilities;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof ImageAssessment))
      return false;
    else {
      ImageAssessment imageAssessment = (ImageAssessment) obj;
      return Objects.equals(this.exploitability, imageAssessment.exploitability)
          && Objects.equals(this.findings, imageAssessment.findings)
          && Objects.equals(this.riskScore, imageAssessment.riskScore)
          && Objects.equals(this.vulnerabilities, imageAssessment.vulnerabilities);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(exploitability, findings, riskScore, vulnerabilities);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ImageAssessment.class.getSimpleName() + "[", "]")
        .add("exploitability=" + exploitability)
        .add("findings=" + findings)
        .add("riskScore=" + riskScore)
        .add("vulnerabilities=" + vulnerabilities)
        .toString();
  }
}
