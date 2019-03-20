package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.StringJoiner;

public class RepositoryDigest {

  @JsonProperty("digest")
  private String digest = null;

  @JsonProperty("registry")
  private String registry = null;

  @JsonProperty("repository")
  private String repository = null;

  public RepositoryDigest digest(String digest) {
    this.digest = digest;
    return this;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public RepositoryDigest registry(String registry) {
    this.registry = registry;
    return this;
  }

  public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public RepositoryDigest repository(String repository) {
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
    else if (!(obj instanceof RepositoryDigest))
      return false;
    else {
      RepositoryDigest repositoryDigest = (RepositoryDigest) obj;
      return Objects.equals(this.digest, repositoryDigest.digest)
          && Objects.equals(this.registry, repositoryDigest.registry)
          && Objects.equals(this.repository, repositoryDigest.repository);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(digest, registry, repository);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RepositoryDigest.class.getSimpleName() + "[", "]")
        .add("digest=" + digest)
        .add("registry=" + registry)
        .add("repository=" + repository)
        .toString();
  }
}
