package com.rapid7.sdlc.plugin.api;

import com.rapid7.sdlc.plugin.api.client.ApiClient;
import com.rapid7.sdlc.plugin.api.model.Build;
import com.rapid7.sdlc.plugin.api.model.BuildIdLink;
import com.rapid7.sdlc.plugin.api.model.NewImageBuild;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Generated code.
 */
public interface BuildApi extends ApiClient.Api {

  /**
   * Build
   * Returns the details of a previously saved build.
   * @param id id (required)
   * @return Build
   */
  @RequestLine("GET /vm/v4/builds/{id}")
  @Headers({
      "Accept: application/json;charset=UTF-8",
  })
  Build getBuild(@Param("id") String id);

  /**
   * Container Image Builds
   * Submits the details of an SDLC container image build for storage and historical tracking.
   * @param newBuildResource newBuildResource (required)
   * @return BuildIdLink
   */
  @RequestLine("POST /vm/v4/builds/image")
  @Headers({
      "Content-Type: application/json",
      "Accept: application/json;charset=UTF-8",
  })
  BuildIdLink newImageBuild(NewImageBuild newBuildResource);
}

