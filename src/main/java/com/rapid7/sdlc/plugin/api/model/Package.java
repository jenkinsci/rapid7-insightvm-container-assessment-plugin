package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.StringJoiner;

public class Package {

  @JsonProperty("assessment")
  private ImageAssessment assessment = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("epoch")
  private String epoch = null;

  @JsonProperty("home_page")
  private String homePage = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("license")
  private String license = null;

  @JsonProperty("maintainer")
  private String maintainer = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("os_architecture")
  private String osArchitecture = null;

  @JsonProperty("os_family")
  private String osFamily = null;

  @JsonProperty("os_name")
  private String osName = null;

  @JsonProperty("os_vendor")
  private String osVendor = null;

  @JsonProperty("os_version")
  private String osVersion = null;

  @JsonProperty("release")
  private String release = null;

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("source")
  private String source = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    APKG("APKG"),
    
    DPKG("DPKG"),
    
    PACMAN("PACMAN"),
    
    RPM("RPM"),
    
    UNKNOWN("UNKNOWN");

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
        if (String.valueOf(b.value).equalsIgnoreCase(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("version")
  private String version = null;

  public Package assessment(ImageAssessment assessment) {
    this.assessment = assessment;
    return this;
  }

  public ImageAssessment getAssessment() {
    return assessment;
  }

  public void setAssessment(ImageAssessment assessment) {
    this.assessment = assessment;
  }

  public Package description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Package epoch(String epoch) {
    this.epoch = epoch;
    return this;
  }

  public String getEpoch() {
    return epoch;
  }

  public void setEpoch(String epoch) {
    this.epoch = epoch;
  }

  public Package homePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public Package id(Long id) {
    this.id = id;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Package license(String license) {
    this.license = license;
    return this;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public Package maintainer(String maintainer) {
    this.maintainer = maintainer;
    return this;
  }

  public String getMaintainer() {
    return maintainer;
  }

  public void setMaintainer(String maintainer) {
    this.maintainer = maintainer;
  }

  public Package name(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Package osArchitecture(String osArchitecture) {
    this.osArchitecture = osArchitecture;
    return this;
  }

  public String getOsArchitecture() {
    return osArchitecture;
  }

  public void setOsArchitecture(String osArchitecture) {
    this.osArchitecture = osArchitecture;
  }

  public Package osFamily(String osFamily) {
    this.osFamily = osFamily;
    return this;
  }

  public String getOsFamily() {
    return osFamily;
  }

  public void setOsFamily(String osFamily) {
    this.osFamily = osFamily;
  }

  public Package osName(String osName) {
    this.osName = osName;
    return this;
  }

  public String getOsName() {
    return osName;
  }

  public void setOsName(String osName) {
    this.osName = osName;
  }

  public Package osVendor(String osVendor) {
    this.osVendor = osVendor;
    return this;
  }

  public String getOsVendor() {
    return osVendor;
  }

  public void setOsVendor(String osVendor) {
    this.osVendor = osVendor;
  }

  public Package osVersion(String osVersion) {
    this.osVersion = osVersion;
    return this;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public Package release(String release) {
    this.release = release;
    return this;
  }

  public String getRelease() {
    return release;
  }

  public void setRelease(String release) {
    this.release = release;
  }

  public Package size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public Package source(String source) {
    this.source = source;
    return this;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Package type(TypeEnum type) {
    this.type = type;
    return this;
  }

  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Package version(String version) {
    this.version = version;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Package))
      return false;
    else {
      Package pkg = (Package) obj;
      return Objects.equals(this.assessment, pkg.assessment)
          && Objects.equals(this.description, pkg.description)
          && Objects.equals(this.epoch, pkg.epoch)
          && Objects.equals(this.homePage, pkg.homePage)
          && Objects.equals(this.id, pkg.id)
          && Objects.equals(this.license, pkg.license)
          && Objects.equals(this.maintainer, pkg.maintainer)
          && Objects.equals(this.name, pkg.name)
          && Objects.equals(this.osArchitecture, pkg.osArchitecture)
          && Objects.equals(this.osFamily, pkg.osFamily)
          && Objects.equals(this.osName, pkg.osName)
          && Objects.equals(this.osVendor, pkg.osVendor)
          && Objects.equals(this.osVersion, pkg.osVersion)
          && Objects.equals(this.release, pkg.release)
          && Objects.equals(this.size, pkg.size)
          && Objects.equals(this.source, pkg.source)
          && Objects.equals(this.type, pkg.type)
          && Objects.equals(this.version, pkg.version);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(assessment, description, epoch, homePage, id, license, maintainer, name, osArchitecture, osFamily, osName, osVendor, osVersion, release, size, source, type, version);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Package.class.getSimpleName() + "[", "]")
        .add("assessment=" + assessment)
        .add("description=" + description)
        .add("epoch=" + epoch)
        .add("homePage=" + homePage)
        .add("id=" + id)
        .add("license=" + license)
        .add("maintainer=" + maintainer)
        .add("name=" + name)
        .add("osArchitecture=" + osArchitecture)
        .add("osFamily=" + osFamily)
        .add("osName=" + osName)
        .add("osVendor=" + osVendor)
        .add("osVersion=" + osVersion)
        .add("release=" + release)
        .add("size=" + size)
        .add("source=" + source)
        .add("type=" + type)
        .add("version=" + version)
        .toString();
  }
}
