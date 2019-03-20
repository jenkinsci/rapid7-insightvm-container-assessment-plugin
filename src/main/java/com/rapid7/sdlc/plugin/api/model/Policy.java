package com.rapid7.sdlc.plugin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Policy {

  @JsonProperty("links")
  private List<Link> links = new ArrayList<>();

  @JsonProperty("rules")
  private List<Rule> rules = new ArrayList<>();

  public Policy links(List<Link> links) {
    this.links = links;
    return this;
  }

  public Policy addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public Policy rules(List<Rule> rules) {
    this.rules = rules;
    return this;
  }

  public Policy addRulesItem(Rule rulesItem) {
    this.rules.add(rulesItem);
    return this;
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (!(obj instanceof Policy))
      return false;
    else {
      Policy policy = (Policy) obj;
      return Objects.equals(this.links, policy.links)
          && Objects.equals(this.rules, policy.rules);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, rules);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Policy.class.getSimpleName() + "[", "]")
        .add("links=" + links)
        .add("rules=" + rules)
        .toString();
  }
}
