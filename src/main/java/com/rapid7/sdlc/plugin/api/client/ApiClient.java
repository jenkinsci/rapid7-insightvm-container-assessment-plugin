package com.rapid7.sdlc.plugin.api.client;

import com.rapid7.sdlc.plugin.api.client.auth.ApiKeyAuth;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.tools.javac.util.List;
import feign.Feign;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import hudson.ProxyConfiguration;
import hudson.Util;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Authenticator;
import okhttp3.Credentials;

/**
 * Generated code.
 */
public class ApiClient {
  public interface Api {}

  protected ObjectMapper objectMapper;
  private String basePath = "https://localhost:9999";
  private Map<String, RequestInterceptor> apiAuthorizations;
  private Feign.Builder feignBuilder;

  public ApiClient() {
    objectMapper = createObjectMapper();
    apiAuthorizations = new LinkedHashMap<>();
    feignBuilder = Feign.builder()
        .client(new OkHttpClient())
        .encoder(new JacksonEncoder(objectMapper))
        .decoder(new JacksonDecoder(objectMapper))
        .logger(new Slf4jLogger())
        .retryer(new CustomRetryer(List.of(404)));
  }

  public ApiClient(String[] authNames) {
    this();
    for (String authName : authNames) {
      RequestInterceptor auth;
      if ("api-key".equals(authName)) {
        auth = new ApiKeyAuth("header", "X-Api-Key");
      } else {
        throw new RuntimeException("auth name \"" + authName + "\" not found in available auth names");
      }
      addAuthorization(authName, auth);
    }
  }

  /**
   * Basic constructor for single auth name
   * @param authName
   */
  public ApiClient(String authName) {
    this(new String[]{authName});
  }

  /**
   * Helper constructor for single api key
   * @param authName
   * @param apiKey
   */
  public ApiClient(String authName, String apiKey) {
    this(authName);
    this.setApiKey(apiKey);
  }

  public String getBasePath() {
    return basePath;
  }

  public ApiClient setBasePath(String basePath) {
    this.basePath = basePath;
    return this;
  }

  public Map<String, RequestInterceptor> getApiAuthorizations() {
    return apiAuthorizations;
  }

  public void setApiAuthorizations(Map<String, RequestInterceptor> apiAuthorizations) {
    this.apiAuthorizations = apiAuthorizations;
  }

  public Feign.Builder getFeignBuilder() {
    return feignBuilder;
  }

  public ApiClient setFeignBuilder(Feign.Builder feignBuilder) {
    this.feignBuilder = feignBuilder;
    return this;
  }

  private ObjectMapper createObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.setDateFormat(new RFC3339DateFormat());
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public void setProxy(ProxyConfiguration proxyCfg) {
    okhttp3.OkHttpClient.Builder okHttpBuilder = new okhttp3.OkHttpClient.Builder();
    if (proxyCfg != null) {
      okHttpBuilder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyCfg.name, proxyCfg.port)));

      String proxyUser = Util.fixNull(proxyCfg.getUserName());
      String proxyPassword = Util.fixNull(proxyCfg.getPassword());
      if (!proxyUser.isEmpty() && !proxyPassword.isEmpty()) {
        String credentials = Credentials.basic(proxyUser, proxyPassword);
        Authenticator proxyAuth = (route, response) -> response
            .request()
            .newBuilder()
            .header("Proxy-Authorization", credentials)
            .build();

        okHttpBuilder.proxyAuthenticator(proxyAuth);
      }

      feignBuilder.client(new OkHttpClient(okHttpBuilder.build()));
    }

  }

  /**
   * Creates a feign client for given API interface.
   *
   * Usage:
   *    ApiClient apiClient = new ApiClient();
   *    apiClient.setBasePath("http://localhost:8080");
   *    XYZApi api = apiClient.buildClient(XYZApi.class);
   *    XYZResponse response = api.someMethod(...);
   * @param <T> Type
   * @param clientClass Client class
   * @return The Client
   */
  public <T extends Api> T buildClient(Class<T> clientClass) {
    return feignBuilder.target(clientClass, basePath);
  }

  /**
   * Select the Accept header's value from the given accepts array:
   *   if JSON exists in the given array, use it;
   *   otherwise use all of them (joining into a string)
   *
   * @param accepts The accepts array to select from
   * @return The Accept header to use. If the given array is empty,
   *   null will be returned (not to set the Accept header explicitly).
   */
  public String selectHeaderAccept(String[] accepts) {
    if (accepts.length == 0) return null;
    if (StringUtil.containsIgnoreCase(accepts, "application/json")) return "application/json";
    return StringUtil.join(accepts, ",");
  }

  /**
   * Select the Content-Type header's value from the given array:
   *   if JSON exists in the given array, use it;
   *   otherwise use the first one of the array.
   *
   * @param contentTypes The Content-Type array to select from
   * @return The Content-Type header to use. If the given array is empty,
   *   JSON will be used.
   */
  public String selectHeaderContentType(String[] contentTypes) {
    if (contentTypes.length == 0) return "application/json";
    if (StringUtil.containsIgnoreCase(contentTypes, "application/json")) return "application/json";
    return contentTypes[0];
  }

  /**
   * Helper method to configure the first api key found
   * @param apiKey API key
   */
  public void setApiKey(String apiKey) {
    for (RequestInterceptor apiAuthorization : apiAuthorizations.values()) {
      if (apiAuthorization instanceof ApiKeyAuth) {
        ApiKeyAuth keyAuth = (ApiKeyAuth) apiAuthorization;
        keyAuth.setApiKey(apiKey);
        return ;
      }
    }
    throw new RuntimeException("No API key authentication configured!");
  }

  /**
   * Gets request interceptor based on authentication name
   * @param authName Authentiation name
   * @return Request Interceptor
   */
  public RequestInterceptor getAuthorization(String authName) {
    return apiAuthorizations.get(authName);
  }

  /**
   * Adds an authorization to be used by the client
   * @param authName Authentication name
   * @param authorization Request interceptor
   */
  public void addAuthorization(String authName, RequestInterceptor authorization) {
    if (apiAuthorizations.containsKey(authName)) {
      throw new RuntimeException("auth name \"" + authName + "\" already in api authorizations");
    }
    apiAuthorizations.put(authName, authorization);
    feignBuilder.requestInterceptor(authorization);
  }
}
