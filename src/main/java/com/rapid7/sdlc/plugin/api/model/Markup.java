package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Markup {

  @JsonProperty("html")
  private String html = null;

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("text")
  private String text = null;

  public Markup html(String html) {
    this.html = html;
    return this;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public Markup links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Markup addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public Markup text(String text) {
    this.text = text;
    return this;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Markup))
      return false;
    else {
      Markup markup = (Markup) obj;
      return Objects.equals(this.html, markup.html)
          && Objects.equals(this.links, markup.links)
          && Objects.equals(this.text, markup.text);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(html, links, text);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Markup.class.getSimpleName() + "[", "]")
        .add("html=" + html)
        .add("links=" + links)
        .add("text=" + text)
        .toString();
  }
}
