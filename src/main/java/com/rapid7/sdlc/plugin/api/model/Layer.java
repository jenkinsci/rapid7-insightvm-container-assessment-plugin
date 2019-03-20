package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Layer {

  @JsonProperty("assessment")
  private ImageAssessment assessment = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("command")
  private DockerfileCommand command = null;

  @JsonProperty("commands")
  private String commands = null;

  @JsonProperty("comment")
  private String comment = null;

  @JsonProperty("created")
  private String created = null;

  @JsonProperty("empty")
  private Boolean empty = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("operating_system")
  private ImageOperatingSystem operatingSystem = null;

  @JsonProperty("package_count")
  private Integer packageCount = null;

  @JsonProperty("packages")
  private List<Package> packages = new ArrayList<>();

  @JsonProperty("parent_id")
  private String parentId = null;

  @JsonProperty("position")
  private Integer position = null;

  @JsonProperty("size")
  private Long size = null;

  public Layer assessment(ImageAssessment assessment) {
    this.assessment = assessment;
    return this;
  }

  public ImageAssessment getAssessment() {
    return assessment;
  }

  public void setAssessment(ImageAssessment assessment) {
    this.assessment = assessment;
  }

  public Layer author(String author) {
    this.author = author;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Layer command(DockerfileCommand command) {
    this.command = command;
    return this;
  }

  public DockerfileCommand getCommand() {
    return command;
  }

  public void setCommand(DockerfileCommand command) {
    this.command = command;
  }

  public Layer commands(String commands) {
    this.commands = commands;
    return this;
  }

  public String getCommands() {
    return commands;
  }

  public void setCommands(String commands) {
    this.commands = commands;
  }

  public Layer comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Layer created(String created) {
    this.created = created;
    return this;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public Layer empty(Boolean empty) {
    this.empty = empty;
    return this;
  }

  public Boolean isEmpty() {
    return empty;
  }

  public void setEmpty(Boolean empty) {
    this.empty = empty;
  }

  public Layer id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Layer operatingSystem(ImageOperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

  public ImageOperatingSystem getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(ImageOperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public Layer packageCount(Integer packageCount) {
    this.packageCount = packageCount;
    return this;
  }

  public Integer getPackageCount() {
    return packageCount;
  }

  public void setPackageCount(Integer packageCount) {
    this.packageCount = packageCount;
  }

  public Layer packages(List<Package> packages) {
    this.packages = packages;
    return this;
  }

  public Layer addPackagesItem(Package packagesItem) {
    this.packages.add(packagesItem);
    return this;
  }

  public List<Package> getPackages() {
    return packages;
  }

  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  public Layer parentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public Layer position(Integer position) {
    this.position = position;
    return this;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Layer size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Layer))
      return false;
    else {
      Layer layer = (Layer) obj;
      return Objects.equals(this.assessment, layer.assessment)
          && Objects.equals(this.author, layer.author)
          && Objects.equals(this.command, layer.command)
          && Objects.equals(this.commands, layer.commands)
          && Objects.equals(this.comment, layer.comment)
          && Objects.equals(this.created, layer.created)
          && Objects.equals(this.empty, layer.empty)
          && Objects.equals(this.id, layer.id)
          && Objects.equals(this.operatingSystem, layer.operatingSystem)
          && Objects.equals(this.packageCount, layer.packageCount)
          && Objects.equals(this.packages, layer.packages)
          && Objects.equals(this.parentId, layer.parentId)
          && Objects.equals(this.position, layer.position)
          && Objects.equals(this.size, layer.size);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(assessment, author, command, commands, comment, created, empty, id, operatingSystem, packageCount, packages, parentId, position, size);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Layer.class.getSimpleName() + "[", "]")
        .add("assessment=" + assessment)
        .add("author=" + author)
        .add("command=" + command)
        .add("commands=" + commands)
        .add("comment=" + comment)
        .add("created=" + created)
        .add("empty=" + empty)
        .add("id=" + id)
        .add("operatingSystem=" + operatingSystem)
        .add("packageCount=" + packageCount)
        .add("packages=" + packages)
        .add("parentId=" + parentId)
        .add("position=" + position)
        .add("size=" + size)
        .toString();
  }
}
