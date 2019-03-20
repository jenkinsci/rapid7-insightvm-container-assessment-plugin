package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Links {

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  public Links links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Links addLinksItem(Link linksItem) {
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
    else if (!(obj instanceof Links))
      return false;
    else {
      Links links = (Links) obj;
      return Objects.equals(this.links, links.links);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(links);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Links.class.getSimpleName() + "[", "]")
        .add("links=" + links)
        .toString();
  }
}
