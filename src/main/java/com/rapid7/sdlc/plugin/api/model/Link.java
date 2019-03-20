package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.StringJoiner;

public class Link {

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("rel")
  private String rel = null;

  public Link href(String href) {
    this.href = href;
    return this;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public Link rel(String rel) {
    this.rel = rel;
    return this;
  }

  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Link))
      return false;
    else {
      Link link = (Link) obj;
      return Objects.equals(this.href, link.href)
          && Objects.equals(this.rel, link.rel);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, rel);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Link.class.getSimpleName() + "[", "]")
        .add("href=" + href)
        .add("rel=" + rel)
        .toString();
  }
}
