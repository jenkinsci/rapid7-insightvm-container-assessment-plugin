package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.StringJoiner;

public class NewImageBuild {

  @JsonProperty("artifact_id")
  private String artifactId = null;

  @JsonProperty("build_version")
  private String buildVersion = null;

  @JsonProperty("number")
  private String number = null;

  @JsonProperty("platform")
  private String platform = null;

  @JsonProperty("policy")
  private Policy policy = null;

  @JsonProperty("project_id")
  private String projectId = null;

  @JsonProperty("start")
  private String start = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("system_id")
  private String systemId = null;

  @JsonProperty("version")
  private String version = null;

  public NewImageBuild artifactId(String artifactId) {
    this.artifactId = artifactId;
    return this;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public NewImageBuild buildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
    return this;
  }

  public String getBuildVersion() {
    return buildVersion;
  }

  public void setBuildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
  }

  public NewImageBuild number(String number) {
    this.number = number;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public NewImageBuild platform(String platform) {
    this.platform = platform;
    return this;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public NewImageBuild policy(Policy policy) {
    this.policy = policy;
    return this;
  }

  public Policy getPolicy() {
    return policy;
  }

  public void setPolicy(Policy policy) {
    this.policy = policy;
  }

  public NewImageBuild projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public NewImageBuild start(String start) {
    this.start = start;
    return this;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public NewImageBuild status(String status) {
    this.status = status;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public NewImageBuild systemId(String systemId) {
    this.systemId = systemId;
    return this;
  }

  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  public NewImageBuild version(String version) {
    this.version = version;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof NewImageBuild))
      return false;
    else {
      NewImageBuild newImageBuild = (NewImageBuild) obj;
      return Objects.equals(this.artifactId, newImageBuild.artifactId)
          && Objects.equals(this.buildVersion, newImageBuild.buildVersion)
          && Objects.equals(this.number, newImageBuild.number)
          && Objects.equals(this.platform, newImageBuild.platform)
          && Objects.equals(this.policy, newImageBuild.policy)
          && Objects.equals(this.projectId, newImageBuild.projectId)
          && Objects.equals(this.start, newImageBuild.start)
          && Objects.equals(this.status, newImageBuild.status)
          && Objects.equals(this.systemId, newImageBuild.systemId)
          && Objects.equals(this.version, newImageBuild.version);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactId, buildVersion, number, platform, policy, projectId, start, status, systemId, version);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NewImageBuild.class.getSimpleName() + "[", "]")
        .add("artifactId=" + artifactId)
        .add("buildVersion=" + buildVersion)
        .add("number=" + number)
        .add("platform=" + platform)
        .add("policy=" + policy)
        .add("projectId=" + projectId)
        .add("start=" + start)
        .add("status=" + status)
        .add("systemId=" + systemId)
        .add("version=" + version)
        .toString();
  }
}
