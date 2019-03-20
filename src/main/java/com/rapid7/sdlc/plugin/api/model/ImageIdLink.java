package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ImageIdLink {

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  public ImageIdLink id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ImageIdLink links(List<Link> links) {
    this.links = links;
    return this;
  }

  public ImageIdLink addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof ImageIdLink))
      return false;
    else {
      ImageIdLink imageIdLink = (ImageIdLink) obj;
      return Objects.equals(this.id, imageIdLink.id)
          && Objects.equals(this.links, imageIdLink.links);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, links);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ImageIdLink.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("links=" + links)
        .toString();
  }
}
