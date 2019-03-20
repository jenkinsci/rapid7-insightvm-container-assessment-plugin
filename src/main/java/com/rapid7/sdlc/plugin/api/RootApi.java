package com.rapid7.sdlc.plugin.api;

import com.rapid7.sdlc.plugin.api.client.ApiClient;
import com.rapid7.sdlc.plugin.api.model.Links;
import feign.Headers;
import feign.RequestLine;

/**
 * Generated code.
 */
public interface RootApi extends ApiClient.Api {

  /**
   * API Root
   * Returns a listing of resources (endpoints) available in a version of the API.
   * @return Links
   */
  @RequestLine("GET /vm/v4")
  @Headers({
      "Accept: application/json;charset=UTF-8",
  })
  Links rootVersionApi();
}
