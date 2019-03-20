package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.StringJoiner;

public class PageInfo {

  @JsonProperty("number")
  private Long number = null;

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("totalPages")
  private Long totalPages = null;

  @JsonProperty("totalResources")
  private Long totalResources = null;

  public PageInfo number(Long number) {
    this.number = number;
    return this;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public PageInfo size(Long size) {
    this.size = size;
    return this;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public PageInfo totalPages(Long totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  public Long getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Long totalPages) {
    this.totalPages = totalPages;
  }

  public PageInfo totalResources(Long totalResources) {
    this.totalResources = totalResources;
    return this;
  }

  public Long getTotalResources() {
    return totalResources;
  }

  public void setTotalResources(Long totalResources) {
    this.totalResources = totalResources;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof PageInfo))
      return false;
    else {
      PageInfo pageInfo = (PageInfo) obj;
      return Objects.equals(this.number, pageInfo.number)
          && Objects.equals(this.size, pageInfo.size)
          && Objects.equals(this.totalPages, pageInfo.totalPages)
          && Objects.equals(this.totalResources, pageInfo.totalResources);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, size, totalPages, totalResources);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PageInfo.class.getSimpleName() + "[", "]")
        .add("number=" + number)
        .add("size=" + size)
        .add("totalPages=" + totalPages)
        .add("totalResources=" + totalResources)
        .toString();
  }
}
