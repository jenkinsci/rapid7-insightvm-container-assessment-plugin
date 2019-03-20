package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Error {

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("localizedMessage")
  private String localizedMessage = null;

  /**
   * The HTTP status code for the error (same as in the HTTP response).
   */
  public enum StatusEnum {
    _100("100"),
    
    _101("101"),
    
    _102("102"),
    
    _103("103"),
    
    _200("200"),
    
    _201("201"),
    
    _202("202"),
    
    _203("203"),
    
    _204("204"),
    
    _205("205"),
    
    _206("206"),
    
    _207("207"),
    
    _208("208"),
    
    _226("226"),
    
    _300("300"),
    
    _301("301"),
    
    _302("302"),
    
    _303("303"),
    
    _304("304"),
    
    _305("305"),
    
    _307("307"),
    
    _308("308"),
    
    _400("400"),
    
    _401("401"),
    
    _402("402"),
    
    _403("403"),
    
    _404("404"),
    
    _405("405"),
    
    _406("406"),
    
    _407("407"),
    
    _408("408"),
    
    _409("409"),
    
    _410("410"),
    
    _411("411"),
    
    _412("412"),
    
    _413("413"),
    
    _414("414"),
    
    _415("415"),
    
    _416("416"),
    
    _417("417"),
    
    _418("418"),
    
    _419("419"),
    
    _420("420"),
    
    _421("421"),
    
    _422("422"),
    
    _423("423"),
    
    _424("424"),
    
    _426("426"),
    
    _428("428"),
    
    _429("429"),
    
    _431("431"),
    
    _451("451"),
    
    _500("500"),
    
    _501("501"),
    
    _502("502"),
    
    _503("503"),
    
    _504("504"),
    
    _505("505"),
    
    _506("506"),
    
    _507("507"),
    
    _508("508"),
    
    _509("509"),
    
    _510("510"),
    
    _511("511");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public Error links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Error addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public Error localizedMessage(String localizedMessage) {
    this.localizedMessage = localizedMessage;
    return this;
  }

  public String getLocalizedMessage() {
    return localizedMessage;
  }

  public void setLocalizedMessage(String localizedMessage) {
    this.localizedMessage = localizedMessage;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error status(StatusEnum status) {
    this.status = status;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Error))
      return false;
    else {
      Error error = (Error) obj;
      return Objects.equals(this.links, error.links)
          && Objects.equals(this.message, error.message)
          && Objects.equals(this.localizedMessage, error.localizedMessage)
          && Objects.equals(this.status, error.status);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, message, localizedMessage, status);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Error.class.getSimpleName() + "[", "]")
        .add("links=" + links)
        .add("message=" + message)
        .add("localizedMessage=" + localizedMessage)
        .add("status=" + status)
        .toString();
  }
}
