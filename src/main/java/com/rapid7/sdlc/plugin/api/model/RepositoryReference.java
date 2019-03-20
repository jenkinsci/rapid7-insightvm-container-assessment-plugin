package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class RepositoryReference {

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("registry")
  private String registry = null;

  @JsonProperty("repository")
  private String repository = null;

  public RepositoryReference links(List<Link> links) {
    this.links = links;
    return this;
  }

  public RepositoryReference addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public RepositoryReference registry(String registry) {
    this.registry = registry;
    return this;
  }

  public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public RepositoryReference repository(String repository) {
    this.repository = repository;
    return this;
  }

  public String getRepository() {
    return repository;
  }

  public void setRepository(String repository) {
    this.repository = repository;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof RepositoryReference))
      return false;
    else {
      RepositoryReference repositoryReference = (RepositoryReference) obj;
      return Objects.equals(this.links, repositoryReference.links)
          && Objects.equals(this.registry, repositoryReference.registry)
          && Objects.equals(this.repository, repositoryReference.repository);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, registry, repository);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RepositoryReference.class.getSimpleName() + "[", "]")
        .add("links=" + links)
        .add("registry=" + registry)
        .add("repository=" + repository)
        .toString();
  }
}
