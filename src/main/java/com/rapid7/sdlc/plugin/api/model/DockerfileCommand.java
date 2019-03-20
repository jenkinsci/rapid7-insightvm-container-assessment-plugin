package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class DockerfileCommand {

  @JsonProperty("commands")
  private List<String> commands = new ArrayList<>();

  @JsonProperty("operands")
  private List<String> operands = new ArrayList<>();

  @JsonProperty("original")
  private String original = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    FROM("FROM"),
    
    RUN("RUN"),
    
    CMD("CMD"),
    
    LABEL("LABEL"),
    
    MAINTAINER("MAINTAINER"),
    
    EXPOSE("EXPOSE"),
    
    ENV("ENV"),
    
    ADD("ADD"),
    
    COPY("COPY"),
    
    ENTRYPOINT("ENTRYPOINT"),
    
    VOLUME("VOLUME"),
    
    USER("USER"),
    
    WORKDIR("WORKDIR"),
    
    ARG("ARG"),
    
    ONBUILD("ONBUILD"),
    
    STOPSIGNAL("STOPSIGNAL"),
    
    HEALTHCHECK("HEALTHCHECK"),
    
    SHELL("SHELL"),
    
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
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  public DockerfileCommand commands(List<String> commands) {
    this.commands = commands;
    return this;
  }

  public DockerfileCommand addCommandsItem(String commandsItem) {
    this.commands.add(commandsItem);
    return this;
  }

  public List<String> getCommands() {
    return commands;
  }

  public void setCommands(List<String> commands) {
    this.commands = commands;
  }

  public DockerfileCommand operands(List<String> operands) {
    this.operands = operands;
    return this;
  }

  public DockerfileCommand addOperandsItem(String operandsItem) {
    this.operands.add(operandsItem);
    return this;
  }

  public List<String> getOperands() {
    return operands;
  }

  public void setOperands(List<String> operands) {
    this.operands = operands;
  }

  public DockerfileCommand original(String original) {
    this.original = original;
    return this;
  }

  public String getOriginal() {
    return original;
  }

  public void setOriginal(String original) {
    this.original = original;
  }

  public DockerfileCommand type(TypeEnum type) {
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
    else if (!(obj instanceof DockerfileCommand))
      return false;
    else {
      DockerfileCommand dockerfileCommand = (DockerfileCommand) obj;
      return Objects.equals(this.commands, dockerfileCommand.commands)
          && Objects.equals(this.operands, dockerfileCommand.operands)
          && Objects.equals(this.original, dockerfileCommand.original)
          && Objects.equals(this.type, dockerfileCommand.type);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(commands, operands, original, type);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DockerfileCommand.class.getSimpleName() + "[", "]")
        .add("commands=" + commands)
        .add("operands=" + operands)
        .add("original=" + original)
        .add("type=" + type)
        .toString();
  }
}
