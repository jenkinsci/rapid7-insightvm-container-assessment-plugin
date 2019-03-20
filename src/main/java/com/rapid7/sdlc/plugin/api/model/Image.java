package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Image {

  @JsonProperty("assessment")
  private ImageAssessment assessment = null;

  @JsonProperty("created")
  private String created = null;

  @JsonProperty("digests")
  private List<RepositoryDigest> digests = new ArrayList<>();

  @JsonProperty("findings")
  private List<ImageVulnerabilityFinding> findings = new ArrayList<>();

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("layer_count")
  private Integer layerCount = null;

  @JsonProperty("layers")
  private List<Layer> layers = new ArrayList<>();

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("operatingSystem")
  private ImageOperatingSystem operatingSystem = null;

  @JsonProperty("package_count")
  private Integer packageCount = null;

  @JsonProperty("packages")
  private List<Package> packages = new ArrayList<>();

  @JsonProperty("repositories")
  private List<RepositoryReference> repositories = new ArrayList<>();

  @JsonProperty("repository")
  private RepositoryReference repository = null;

  @JsonProperty("repository_tags")
  private List<RepositoryTagReference> repositoryTags = new ArrayList<>();

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("tags")
  private List<RepositoryTagReference> tags = new ArrayList<>();

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    DOCKER("docker"),
    
    UNKNOWN("unknown");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  public Image assessment(ImageAssessment assessment) {
    this.assessment = assessment;
    return this;
  }

  public ImageAssessment getAssessment() {
    return assessment;
  }

  public void setAssessment(ImageAssessment assessment) {
    this.assessment = assessment;
  }

  public Image created(String created) {
    this.created = created;
    return this;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public Image digests(List<RepositoryDigest> digests) {
    this.digests = digests;
    return this;
  }

  public Image addDigestsItem(RepositoryDigest digestsItem) {
    this.digests.add(digestsItem);
    return this;
  }

  public List<RepositoryDigest> getDigests() {
    return digests;
  }

  public void setDigests(List<RepositoryDigest> digests) {
    this.digests = digests;
  }

  public Image findings(List<ImageVulnerabilityFinding> findings) {
    this.findings = findings;
    return this;
  }

  public Image addFindingsItem(ImageVulnerabilityFinding findingsItem) {
    this.findings.add(findingsItem);
    return this;
  }

  public List<ImageVulnerabilityFinding> getFindings() {
    return findings;
  }

  public void setFindings(List<ImageVulnerabilityFinding> findings) {
    this.findings = findings;
  }

  public Image id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Image layerCount(Integer layerCount) {
    this.layerCount = layerCount;
    return this;
  }

  public Integer getLayerCount() {
    return layerCount;
  }

  public void setLayerCount(Integer layerCount) {
    this.layerCount = layerCount;
  }

  public Image layers(List<Layer> layers) {
    this.layers = layers;
    return this;
  }

  public Image addLayersItem(Layer layersItem) {
    this.layers.add(layersItem);
    return this;
  }

  public List<Layer> getLayers() {
    return layers;
  }

  public void setLayers(List<Layer> layers) {
    this.layers = layers;
  }

  public Image links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Image addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public Image operatingSystem(ImageOperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

  public ImageOperatingSystem getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(ImageOperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public Image packageCount(Integer packageCount) {
    this.packageCount = packageCount;
    return this;
  }

  public Integer getPackageCount() {
    return packageCount;
  }

  public void setPackageCount(Integer packageCount) {
    this.packageCount = packageCount;
  }

  public Image packages(List<Package> packages) {
    this.packages = packages;
    return this;
  }

  public Image addPackagesItem(Package packagesItem) {
    this.packages.add(packagesItem);
    return this;
  }

  public List<Package> getPackages() {
    return packages;
  }

  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  public Image repositories(List<RepositoryReference> repositories) {
    this.repositories = repositories;
    return this;
  }

  public Image addRepositoriesItem(RepositoryReference repositoriesItem) {
    this.repositories.add(repositoriesItem);
    return this;
  }

  public List<RepositoryReference> getRepositories() {
    return repositories;
  }

  public void setRepositories(List<RepositoryReference> repositories) {
    this.repositories = repositories;
  }

  public Image repository(RepositoryReference repository) {
    this.repository = repository;
    return this;
  }

  public RepositoryReference getRepository() {
    return repository;
  }

  public void setRepository(RepositoryReference repository) {
    this.repository = repository;
  }

  public Image repositoryTags(List<RepositoryTagReference> repositoryTags) {
    this.repositoryTags = repositoryTags;
    return this;
  }

  public Image addRepositoryTagsItem(RepositoryTagReference repositoryTagsItem) {
    this.repositoryTags.add(repositoryTagsItem);
    return this;
  }

  public List<RepositoryTagReference> getRepositoryTags() {
    return repositoryTags;
  }

  public void setRepositoryTags(List<RepositoryTagReference> repositoryTags) {
    this.repositoryTags = repositoryTags;
  }

  public Image size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public Image tags(List<RepositoryTagReference> tags) {
    this.tags = tags;
    return this;
  }

  public Image addTagsItem(RepositoryTagReference tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

  public List<RepositoryTagReference> getTags() {
    return tags;
  }

  public void setTags(List<RepositoryTagReference> tags) {
    this.tags = tags;
  }

  public Image type(TypeEnum type) {
    this.type = type;
    return this;
  }

  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Image))
      return false;
    else {
      Image image = (Image) obj;
      return Objects.equals(this.assessment, image.assessment)
          && Objects.equals(this.created, image.created)
          && Objects.equals(this.digests, image.digests)
          && Objects.equals(this.findings, image.findings)
          && Objects.equals(this.id, image.id)
          && Objects.equals(this.layerCount, image.layerCount)
          && Objects.equals(this.layers, image.layers)
          && Objects.equals(this.links, image.links)
          && Objects.equals(this.operatingSystem, image.operatingSystem)
          && Objects.equals(this.packageCount, image.packageCount)
          && Objects.equals(this.packages, image.packages)
          && Objects.equals(this.repositories, image.repositories)
          && Objects.equals(this.repository, image.repository)
          && Objects.equals(this.repositoryTags, image.repositoryTags)
          && Objects.equals(this.size, image.size)
          && Objects.equals(this.tags, image.tags)
          && Objects.equals(this.type, image.type);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(assessment, created, digests, findings, id, layerCount, layers, links, operatingSystem, packageCount, packages, repositories, repository, repositoryTags, size, tags, type);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Image.class.getSimpleName() + "[", "]")
        .add("assessment=" + assessment)
        .add("created=" + created)
        .add("digests=" + digests)
        .add("findings=" + findings)
        .add("id=" + id)
        .add("layerCount=" + layerCount)
        .add("layers=" + layers)
        .add("links=" + links)
        .add("operatingSystem=" + operatingSystem)
        .add("packageCount=" + packageCount)
        .add("packages=" + packages)
        .add("repositories=" + repositories)
        .add("repository=" + repository)
        .add("repositoryTags=" + repositoryTags)
        .add("size=" + size)
        .add("tags=" + tags)
        .add("type=" + type)
        .toString();
  }
}
