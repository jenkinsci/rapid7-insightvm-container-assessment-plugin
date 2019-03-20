package com.rapid7.sdlc.plugin.jenkins;

import com.cloudbees.plugins.credentials.CredentialsMatchers;
import com.cloudbees.plugins.credentials.CredentialsProvider;
import com.cloudbees.plugins.credentials.common.StandardListBoxModel;
import com.rapid7.sdlc.plugin.api.RootApi;
import com.rapid7.sdlc.plugin.api.client.ApiClient;
import feign.FeignException;
import hudson.Extension;
import hudson.ProxyConfiguration;
import hudson.model.Item;
import hudson.security.ACL;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import hudson.util.Secret;
import java.util.Collections;
import java.util.UUID;
import jenkins.model.GlobalConfiguration;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.verb.POST;
import static com.cloudbees.plugins.credentials.CredentialsMatchers.withId;
import static org.apache.commons.lang.StringUtils.trimToEmpty;

/**
 * Global configuration for the plugin to manage the API endpoint and key.
 */
@Extension
public class InsightVmApiConfiguration extends GlobalConfiguration {

  private String systemId;
  private String credentialsId = null;
  private Region insightRegion = Region.US;
  private String rpmDockerImage = null;

  public static InsightVmApiConfiguration get() {
    return GlobalConfiguration.all().get(InsightVmApiConfiguration.class);
  }

  public InsightVmApiConfiguration() {
    load();
    if (systemId == null) {
      systemId = UUID.randomUUID().toString();
      save();
    }
  }

  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
    // not a user configured property so don't need to save
  }

  public String getCredentialsId() {
    return credentialsId;
  }

  public void setCredentialsId(String credentialsId) {
    this.credentialsId = credentialsId;
    save();
  }

  public Region getInsightRegion() {
    return insightRegion;
  }

  public void setInsightRegion(Region region) {
    insightRegion = region;
    save();
  }

  public String getRpmDockerImage() {
    return rpmDockerImage;
  }

  public void setRpmDockerImage(String rpmDockerImage) {
    this.rpmDockerImage = rpmDockerImage;
    save();
  }

  public ListBoxModel doFillCredentialsIdItems(@AncestorInPath Item item, @QueryParameter String credentialsId) {
    if (!Jenkins.getInstance().hasPermission(Jenkins.ADMINISTER))
      return new StandardListBoxModel().includeCurrentValue(credentialsId);

    return new StandardListBoxModel()
      .includeEmptyValue().includeAs(ACL.SYSTEM, Jenkins.getInstance(), StringCredentials.class)
      .includeCurrentValue(credentialsId);
  }

  @POST
  public FormValidation doTestConnection(@QueryParameter String credentialsId, @QueryParameter Region insightRegion) {
    // skip check if not an admin
    Jenkins.getInstance().checkPermission(Jenkins.ADMINISTER);
    if (!Jenkins.getInstance().hasPermission(Jenkins.ADMINISTER))
      return FormValidation.ok();

    if (credentialsId == null || credentialsId.isEmpty())
      return FormValidation.error(Messages.InsightVmApiConfiguration_APIKeyValidationMissing());

    StringCredentials apiKey = CredentialsMatchers.firstOrNull(
        CredentialsMatchers.filter(
            CredentialsProvider.lookupCredentials(StringCredentials.class, Jenkins.getInstance(), ACL.SYSTEM, Collections.emptyList()),
            withId(trimToEmpty(credentialsId))),
        CredentialsMatchers.allOf(CredentialsMatchers.withId(credentialsId) // TODO: More specificity for type/etc
        ));

    if (apiKey == null)
      return FormValidation.error(Messages.InsightVmApiConfiguration_APIKeyValidationMissing());

    ApiClient client = new ApiClient("api-key", Secret.toString(apiKey.getSecret()));
    client.setBasePath(insightRegion.getEndpoint());
    ProxyConfiguration proxyCfg = Jenkins.getInstance().proxy;
    if (proxyCfg != null)
      client.setProxy(proxyCfg);
    RootApi rootApi = client.buildClient(RootApi.class);
    try {
      rootApi.rootVersionApi();
    } catch (FeignException exception) {
      if (exception.status() == 401)
        return FormValidation.error(Messages.InsightVmApiConfiguration_TestConnectionAuthFailure(insightRegion.getEndpoint()));
      else
        return FormValidation.error(Messages.InsightVmApiConfiguration_TestConnectionFailure(insightRegion.getEndpoint()));
    } catch (Exception exception) {
      return FormValidation.error(Messages.InsightVmApiConfiguration_TestConnectionFailure(insightRegion.getEndpoint()));
    }
    return FormValidation.ok(Messages.InsightVmApiConfiguration_TestConnectionSuccess(insightRegion.getEndpoint()));
  }

  public enum Region {
    US(Messages.InsightVmApiConfiguration_InsightRegionUS(), "https://us.api.insight.rapid7.com"),
    CA(Messages.InsightVmApiConfiguration_InsightRegionCA(), "https://ca.api.insight.rapid7.com"),
    EU(Messages.InsightVmApiConfiguration_InsightRegionEU(), "https://eu.api.insight.rapid7.com"),
    AP(Messages.InsightVmApiConfiguration_InsightRegionAP(), "https://ap.api.insight.rapid7.com"),
    AU(Messages.InsightVmApiConfiguration_InsightRegionAU(), "https://au.api.insight.rapid7.com");

    private String label;
    private String endpoint;

    public static Region getRegionFromLabel(String label) {
      for (Region region : Region.values())
        if (label.equals(region.getLabel()))
          return region;

      throw new IllegalArgumentException(String.format("%s is not a valid Region", label));
    }

    public static Region getRegionFromEndpoint(String endpoint) {
      for (Region region : Region.values())
        if (endpoint.equals(region.getEndpoint()))
          return region;

      throw new IllegalArgumentException(String.format("%s is not a valid Region", endpoint));
    }

    public String getLabel() {
      return label;
    }

    public String getEndpoint() {
      return endpoint;
    }

    Region(String name, String endpoint) {
      label = name;
      this.endpoint = endpoint;
    }
  }
}
