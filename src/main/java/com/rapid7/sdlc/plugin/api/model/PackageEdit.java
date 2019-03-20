package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class PackageEdit {

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("source")
  private String source = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("maintainer")
  private String maintainer = null;

  @JsonProperty("home_page")
  private String homePage = null;

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("license")
  private String license = null;

  @JsonProperty("epoch")
  private String epoch = null;

  @JsonProperty("release")
  private String release = null;

  @JsonProperty("os_vendor")
  private String osVendor = null;

  @JsonProperty("os_family")
  private String osFamily = null;

  @JsonProperty("os_name")
  private String osName = null;

  @JsonProperty("os_version")
  private String osVersion = null;

  @JsonProperty("os_architecture")
  private String osArchitecture = null;

  public PackageEdit links(List<Link> links) {
    this.links = links;
    return this;
  }

  public PackageEdit addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public PackageEdit type(String type) {
    this.type = type;
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PackageEdit source(String source) {
    this.source = source;
    return this;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public PackageEdit name(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PackageEdit version(String version) {
    this.version = version;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public PackageEdit description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PackageEdit maintainer(String maintainer) {
    this.maintainer = maintainer;
    return this;
  }

  public String getMaintainer() {
    return maintainer;
  }

  public void setMaintainer(String maintainer) {
    this.maintainer = maintainer;
  }

  public PackageEdit homePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public PackageEdit size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public PackageEdit license(String license) {
    this.license = license;
    return this;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public PackageEdit epoch(String epoch) {
    this.epoch = epoch;
    return this;
  }

  public String getEpoch() {
    return epoch;
  }

  public void setEpoch(String epoch) {
    this.epoch = epoch;
  }

  public PackageEdit release(String release) {
    this.release = release;
    return this;
  }

  public String getRelease() {
    return release;
  }

  public void setRelease(String release) {
    this.release = release;
  }

  public PackageEdit osVendor(String osVendor) {
    this.osVendor = osVendor;
    return this;
  }

  public String getOsVendor() {
    return osVendor;
  }

  public void setOsVendor(String osVendor) {
    this.osVendor = osVendor;
  }

  public PackageEdit osFamily(String osFamily) {
    this.osFamily = osFamily;
    return this;
  }

  public String getOsFamily() {
    return osFamily;
  }

  public void setOsFamily(String osFamily) {
    this.osFamily = osFamily;
  }

  public PackageEdit osName(String osName) {
    this.osName = osName;
    return this;
  }

  public String getOsName() {
    return osName;
  }

  public void setOsName(String osName) {
    this.osName = osName;
  }

  public PackageEdit osVersion(String osVersion) {
    this.osVersion = osVersion;
    return this;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public PackageEdit osArchitecture(String osArchitecture) {
    this.osArchitecture = osArchitecture;
    return this;
  }

  public String getOsArchitecture() {
    return osArchitecture;
  }

  public void setOsArchitecture(String osArchitecture) {
    this.osArchitecture = osArchitecture;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof PackageEdit))
      return false;
    else {
      PackageEdit packageEdit = (PackageEdit) obj;
      return Objects.equals(this.links, packageEdit.links)
          && Objects.equals(this.type, packageEdit.type)
          && Objects.equals(this.source, packageEdit.source)
          && Objects.equals(this.name, packageEdit.name)
          && Objects.equals(this.version, packageEdit.version)
          && Objects.equals(this.description, packageEdit.description)
          && Objects.equals(this.maintainer, packageEdit.maintainer)
          && Objects.equals(this.homePage, packageEdit.homePage)
          && Objects.equals(this.size, packageEdit.size)
          && Objects.equals(this.license, packageEdit.license)
          && Objects.equals(this.epoch, packageEdit.epoch)
          && Objects.equals(this.release, packageEdit.release)
          && Objects.equals(this.osVendor, packageEdit.osVendor)
          && Objects.equals(this.osFamily, packageEdit.osFamily)
          && Objects.equals(this.osName, packageEdit.osName)
          && Objects.equals(this.osVersion, packageEdit.osVersion)
          && Objects.equals(this.osArchitecture, packageEdit.osArchitecture);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, type, source, name, version, description, maintainer, homePage, size, license, epoch, release, osVendor, osFamily, osName, osVersion, osArchitecture);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PackageEdit.class.getSimpleName() + "[", "]")
        .add("links=" + links)
        .add("type=" + type)
        .add("source=" + source)
        .add("name=" + name)
        .add("version=" + version)
        .add("description=" + description)
        .add("maintainer=" + maintainer)
        .add("homePage=" + homePage)
        .add("size=" + size)
        .add("license=" + license)
        .add("epoch=" + epoch)
        .add("release=" + release)
        .add("osVendor=" + osVendor)
        .add("osFamily=" + osFamily)
        .add("osName=" + osName)
        .add("osVersion=" + osVersion)
        .add("osArchitecture=" + osArchitecture)
        .toString();
  }
}
