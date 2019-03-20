package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class RepositoryTagReference {

  @JsonProperty("digest")
  private String digest = null;

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("registry")
  private String registry = null;

  @JsonProperty("repository")
  private String repository = null;

  public RepositoryTagReference digest(String digest) {
    this.digest = digest;
    return this;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public RepositoryTagReference links(List<Link> links) {
    this.links = links;
    return this;
  }

  public RepositoryTagReference addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public RepositoryTagReference name(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RepositoryTagReference registry(String registry) {
    this.registry = registry;
    return this;
  }

  public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public RepositoryTagReference repository(String repository) {
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
    else if (!(obj instanceof RepositoryTagReference))
      return false;
    else {
      RepositoryTagReference repositoryTagReference = (RepositoryTagReference) obj;
      return Objects.equals(this.digest, repositoryTagReference.digest)
          && Objects.equals(this.links, repositoryTagReference.links)
          && Objects.equals(this.name, repositoryTagReference.name)
          && Objects.equals(this.registry, repositoryTagReference.registry)
          && Objects.equals(this.repository, repositoryTagReference.repository);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(digest, links, name, registry, repository);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RepositoryTagReference.class.getSimpleName() + "[", "]")
        .add("digest=" + digest)
        .add("links=" + links)
        .add("name=" + name)
        .add("registry=" + registry)
        .add("repository=" + repository)
        .toString();
  }
}
