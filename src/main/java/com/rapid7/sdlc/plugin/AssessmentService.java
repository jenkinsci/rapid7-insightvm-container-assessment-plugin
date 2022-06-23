package com.rapid7.sdlc.plugin;

import com.rapid7.container.analyzer.docker.model.image.Image;
import com.rapid7.container.analyzer.docker.model.image.ImageId;
import com.rapid7.sdlc.plugin.api.ContainerApi;
import com.rapid7.sdlc.plugin.api.client.ApiClient;
import com.rapid7.sdlc.plugin.api.model.ImageEdit;
import com.rapid7.sdlc.plugin.api.model.ImageOperatingSystem;
import com.rapid7.sdlc.plugin.api.model.LayerEdit;
import com.rapid7.sdlc.plugin.api.model.PackageEdit;
import com.rapid7.sdlc.plugin.api.model.RepositoryDigest;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import static java.util.stream.Collectors.toList;

/**
 * Provides functionality for fetching reports from the Rapid7 vulnerability
 * assessment service. Allows uploading either a full tarball or a locally-
 * generated image analysis.
 */
public class AssessmentService {

  private ContainerApi containerApi;

  public AssessmentService(ApiClient apiClient) {
    containerApi = apiClient.buildClient(ContainerApi.class);
    BasicConfigurator.configure();
  }

  /**
   * Uploads image analysis results to the Rapid7 assessment API.
   * This function returns without waiting for a response. The user must
   * poll the service using pollResults().
   *
   * @param image The analysis results to upload.
   * @see AssessmentService#getImage(ImageId)
   */
  public void uploadImageAnalysis(Image image) {
    containerApi.saveImage(convert(image));
  }

  private ImageEdit convert(Image image) {
    List<RepositoryDigest> digests = new ArrayList<>();
    if(image != null && image.getDigest() != null) {
      digests.add(new RepositoryDigest().digest(image.getDigest().getString()));
    }
    return new ImageEdit()
        .created(image.getCreated().toString())
        .digests(digests)
        .id(image.getId().getString())
        .layers(image.getLayers().stream().map(this::convert).collect(toList()))
        .size(image.getSize())
        .type(image.getType().name());
  }

  private LayerEdit convert(com.rapid7.container.analyzer.docker.model.image.Layer layer) {
    LayerEdit convertedLayer = new LayerEdit();
    convertedLayer
        .author(layer.getAuthor())
        .commands(layer.getCommand())
        .comment(layer.getComment())
        .created(layer.getCreated().toString())
        .empty(layer.isEmpty())
        .id(layer.getId().getString())
        .operatingSystem(layer.getOperatingSystem() == null ? null : convert(layer.getOperatingSystem()))
        .packages(layer.getPackages().stream().map(this::convert).collect(toList()))
        .parentId(layer.getParentId() == null ? null : layer.getParentId().getString())
        .size(layer.getSize());

    return convertedLayer;
  }

  private ImageOperatingSystem convert(com.rapid7.container.analyzer.docker.model.image.OperatingSystem os) {
    ImageOperatingSystem convertedOs = new ImageOperatingSystem();
    convertedOs
        .architecture(os.getArchitecture())
        .description(os.getDescription())
        .family(os.getFamily())
        .name(os.getFamily())
        .vendor(os.getVendor())
        .version(os.getVersion());

    return convertedOs;
  }

  private PackageEdit convert(com.rapid7.container.analyzer.docker.model.image.Package pkg) {
    PackageEdit convertedPackage = new PackageEdit();
    convertedPackage
        .description(pkg.getDescription())
        .epoch(pkg.getEpoch())
        .homePage(pkg.getHomepage())
        .license(pkg.getLicense())
        .maintainer(pkg.getMaintainer())
        .name(pkg.getPackage())
        .osFamily(pkg.getOsFamily())
        .osName(pkg.getOsName())
        .osVendor(pkg.getOsVendor())
        .osVersion(pkg.getOsVersion())
        .osArchitecture(pkg.getOsArchitecture())
        .release(pkg.getRelease())
        .size(pkg.getSize())
        .source(pkg.getSource())
        .type(pkg.getType().name())
        .version(pkg.getVersion());

    return convertedPackage;
  }

  public com.rapid7.sdlc.plugin.api.model.Image getImage(ImageId imageId) {
    return containerApi.getImage(imageId.getString());
  }
}
