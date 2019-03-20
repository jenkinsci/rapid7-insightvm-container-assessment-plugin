package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BuildIdLink {

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  public BuildIdLink id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BuildIdLink links(List<Link> links) {
    this.links = links;
    return this;
  }

  public BuildIdLink addLinksItem(Link linksItem) {
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
    else if (!(obj instanceof BuildIdLink))
      return false;
    else {
      BuildIdLink buildIdLink = (BuildIdLink) obj;
      return Objects.equals(this.id, buildIdLink.id)
          && Objects.equals(this.links, buildIdLink.links);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, links);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BuildIdLink.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("links=" + links)
        .toString();
  }
}
