package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Build {

  @JsonProperty("artifact_id")
  private String artifactId = null;

  @JsonProperty("buildId")
  private String buildId = null;

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("number")
  private String number = null;

  @JsonProperty("policy")
  private Policy policy = null;

  @JsonProperty("start")
  private String start = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    SUCCESS("SUCCESS"),
    
    FAILURE("FAILURE"),
    
    UNSTABLE("UNSTABLE");

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

  public Build artifactId(String artifactId) {
    this.artifactId = artifactId;
    return this;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public Build buildId(String buildId) {
    this.buildId = buildId;
    return this;
  }

  public String getBuildId() {
    return buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }

  public Build links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Build addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public Build number(String number) {
    this.number = number;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Build policy(Policy policy) {
    this.policy = policy;
    return this;
  }

  public Policy getPolicy() {
    return policy;
  }

  public void setPolicy(Policy policy) {
    this.policy = policy;
  }

  public Build start(String start) {
    this.start = start;
    return this;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public Build status(StatusEnum status) {
    this.status = status;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Build))
      return false;
    else {
      Build build = (Build) obj;
      return Objects.equals(this.artifactId, build.artifactId)
          && Objects.equals(this.buildId, build.buildId)
          && Objects.equals(this.links, build.links)
          && Objects.equals(this.number, build.number)
          && Objects.equals(this.policy, build.policy)
          && Objects.equals(this.start, build.start)
          && Objects.equals(this.status, build.status);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactId, buildId, links, number, policy, start, status);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Build.class.getSimpleName() + "[", "]")
        .add("artifactId=" + artifactId)
        .add("buildId=" + buildId)
        .add("links=" + links)
        .add("number=" + number)
        .add("policy=" + policy)
        .add("start=" + start)
        .add("status=" + status)
        .toString();
  }
}
