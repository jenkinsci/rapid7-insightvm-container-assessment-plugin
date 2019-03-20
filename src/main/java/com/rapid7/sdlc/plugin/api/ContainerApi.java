package com.rapid7.sdlc.plugin.api;

import com.rapid7.sdlc.plugin.api.client.ApiClient;
import com.rapid7.sdlc.plugin.api.model.Image;
import com.rapid7.sdlc.plugin.api.model.ImageEdit;
import com.rapid7.sdlc.plugin.api.model.ImageIdLink;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Generated code.
 */
public interface ContainerApi extends ApiClient.Api {

  /**
   * Image
   * Returns the details of previously saved image.
   * @param id The identifier of the image, in hash format &#x60;[&lt;algorithm&gt;:]&lt;hash&gt;&#x60;. (required)
   * @return Image
   */
  @RequestLine("GET /vm/v4/images/{id}")
  @Headers({
      "Accept: application/json;charset=UTF-8",
  })
  Image getImage(@Param("id") String id);

  /**
   * Image
   * ${image.post.description}
   * @param imageResource imageResource (required)
   * @return ImageIdLink
   */
  @RequestLine("POST /vm/v4/images")
  @Headers({
      "Content-Type: application/json",
      "Accept: application/json;charset=UTF-8",
  })
  ImageIdLink saveImage(ImageEdit imageResource);
}
