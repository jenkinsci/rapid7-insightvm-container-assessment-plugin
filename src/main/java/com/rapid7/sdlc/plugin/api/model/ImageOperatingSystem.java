package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.StringJoiner;

public class ImageOperatingSystem {

  @JsonProperty("architecture")
  private String architecture = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("family")
  private String family = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("system_name")
  private String systemName = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("vendor")
  private String vendor = null;

  @JsonProperty("version")
  private String version = null;

  public ImageOperatingSystem architecture(String architecture) {
    this.architecture = architecture;
    return this;
  }

  public String getArchitecture() {
    return architecture;
  }

  public void setArchitecture(String architecture) {
    this.architecture = architecture;
  }

  public ImageOperatingSystem description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ImageOperatingSystem family(String family) {
    this.family = family;
    return this;
  }

  public String getFamily() {
    return family;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public ImageOperatingSystem name(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ImageOperatingSystem systemName(String systemName) {
    this.systemName = systemName;
    return this;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public ImageOperatingSystem type(String type) {
    this.type = type;
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ImageOperatingSystem vendor(String vendor) {
    this.vendor = vendor;
    return this;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public ImageOperatingSystem version(String version) {
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
    else if (!(obj instanceof ImageOperatingSystem))
      return false;
    else {
      ImageOperatingSystem imageOperatingSystem = (ImageOperatingSystem) obj;
      return Objects.equals(this.architecture, imageOperatingSystem.architecture)
          && Objects.equals(this.description, imageOperatingSystem.description)
          && Objects.equals(this.family, imageOperatingSystem.family)
          && Objects.equals(this.name, imageOperatingSystem.name)
          && Objects.equals(this.systemName, imageOperatingSystem.systemName)
          && Objects.equals(this.type, imageOperatingSystem.type)
          && Objects.equals(this.vendor, imageOperatingSystem.vendor)
          && Objects.equals(this.version, imageOperatingSystem.version);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(architecture, description, family, name, systemName, type, vendor, version);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ImageOperatingSystem.class.getSimpleName() + "[", "]")
        .add("architecture=" + architecture)
        .add("description=" + description)
        .add("family=" + family)
        .add("name=" + name)
        .add("systemName=" + systemName)
        .add("type=" + type)
        .add("vendor=" + vendor)
        .add("version=" + version)
        .toString();
  }
}
