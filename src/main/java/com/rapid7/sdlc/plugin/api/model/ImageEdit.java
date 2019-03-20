package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ImageEdit {

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("created")
  private String created = null;

  @JsonProperty("digests")
  private List<RepositoryDigest> digests = new ArrayList<>();

  @JsonProperty("layers")
  private List<LayerEdit> layers = new ArrayList<>();

  public ImageEdit id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ImageEdit type(String type) {
    this.type = type;
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ImageEdit size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public ImageEdit created(String created) {
    this.created = created;
    return this;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public ImageEdit digests(List<RepositoryDigest> digests) {
    this.digests = digests;
    return this;
  }

  public ImageEdit addDigestsItem(RepositoryDigest digestsItem) {
    this.digests.add(digestsItem);
    return this;
  }

  public List<RepositoryDigest> getDigests() {
    return digests;
  }

  public void setDigests(List<RepositoryDigest> digests) {
    this.digests = digests;
  }

  public ImageEdit layers(List<LayerEdit> layers) {
    this.layers = layers;
    return this;
  }

  public ImageEdit addLayersItem(LayerEdit layersItem) {
    this.layers.add(layersItem);
    return this;
  }

  public List<LayerEdit> getLayers() {
    return layers;
  }

  public void setLayers(List<LayerEdit> layers) {
    this.layers = layers;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof ImageEdit))
      return false;
    else {
      ImageEdit imageEdit = (ImageEdit) obj;
      return Objects.equals(this.id, imageEdit.id)
          && Objects.equals(this.type, imageEdit.type)
          && Objects.equals(this.size, imageEdit.size)
          && Objects.equals(this.created, imageEdit.created)
          && Objects.equals(this.digests, imageEdit.digests)
          && Objects.equals(this.layers, imageEdit.layers);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, size, created, digests, layers);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ImageEdit.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("type=" + type)
        .add("size=" + size)
        .add("created=" + created)
        .add("digests=" + digests)
        .add("layers=" + layers)
        .toString();
  }
}
