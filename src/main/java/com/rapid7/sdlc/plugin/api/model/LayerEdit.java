package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class LayerEdit {

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("parent_id")
  private String parentId = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("created")
  private String created = null;

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("empty")
  private Boolean empty = null;

  @JsonProperty("commands")
  private String commands = null;

  @JsonProperty("comment")
  private String comment = null;

  @JsonProperty("operating_system")
  private ImageOperatingSystem operatingSystem = null;

  @JsonProperty("packages")
  private List<PackageEdit> packages = new ArrayList<>();

  public LayerEdit id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LayerEdit parentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public LayerEdit author(String author) {
    this.author = author;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LayerEdit created(String created) {
    this.created = created;
    return this;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public LayerEdit size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public LayerEdit empty(Boolean empty) {
    this.empty = empty;
    return this;
  }

  public Boolean isEmpty() {
    return empty;
  }

  public void setEmpty(Boolean empty) {
    this.empty = empty;
  }

  public LayerEdit commands(String commands) {
    this.commands = commands;
    return this;
  }

  public String getCommands() {
    return commands;
  }

  public void setCommands(String commands) {
    this.commands = commands;
  }

  public LayerEdit comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public LayerEdit operatingSystem(ImageOperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

  public ImageOperatingSystem getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(ImageOperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public LayerEdit packages(List<PackageEdit> packages) {
    this.packages = packages;
    return this;
  }

  public LayerEdit addPackagesItem(PackageEdit packagesItem) {
    this.packages.add(packagesItem);
    return this;
  }

  public List<PackageEdit> getPackages() {
    return packages;
  }

  public void setPackages(List<PackageEdit> packages) {
    this.packages = packages;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof LayerEdit))
      return false;
    else {
      LayerEdit layerEdit = (LayerEdit) obj;
      return Objects.equals(this.id, layerEdit.id)
          && Objects.equals(this.parentId, layerEdit.parentId)
          && Objects.equals(this.author, layerEdit.author)
          && Objects.equals(this.created, layerEdit.created)
          && Objects.equals(this.size, layerEdit.size)
          && Objects.equals(this.empty, layerEdit.empty)
          && Objects.equals(this.commands, layerEdit.commands)
          && Objects.equals(this.comment, layerEdit.comment)
          && Objects.equals(this.operatingSystem, layerEdit.operatingSystem)
          && Objects.equals(this.packages, layerEdit.packages);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, author, created, size, empty, commands, comment, operatingSystem, packages);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", LayerEdit.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("parentId=" + parentId)
        .add("author=" + author)
        .add("created=" + created)
        .add("size=" + size)
        .add("empty=" + empty)
        .add("commands=" + commands)
        .add("comment=" + comment)
        .add("operatingSystem=" + operatingSystem)
        .add("packages=" + packages)
        .toString();
  }
}
