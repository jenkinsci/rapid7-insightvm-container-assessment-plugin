package com.rapid7.sdlc.plugin.ruleset.property;

import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.Package;
import com.rapid7.sdlc.plugin.ruleset.CriterionName;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

/**
 * A property that is satisfied if there exists any package with a name substring-matched
 * by the given pattern.
 */
public class PackageName extends StringContainsPropertyEvaluator {

  public static final String displayName = "Any package name contains";

  public PackageName() {

  }

  public PackageName(String pattern) {
    super(pattern);
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public Set<Package> getQualifyingPackages(Image image) {
    return image.getPackages().stream().filter(pkg -> pkg.getName().toLowerCase().contains(pattern.toLowerCase())).collect(Collectors.toSet());
  }

  @Override
  public String getCriterionName() {
    return CriterionName.PACKAGE_NAME.name();
  }

  @Override
  public List<String> getMatches(Image image) {
    return image.getPackages().stream().map(Package::getName).filter(name -> name.toLowerCase().contains(pattern.toLowerCase())).collect(toList());
  }
}
