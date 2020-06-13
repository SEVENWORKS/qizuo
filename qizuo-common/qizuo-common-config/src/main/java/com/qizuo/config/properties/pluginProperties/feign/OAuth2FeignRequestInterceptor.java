/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.pluginProperties.feign;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.Assert;

/** feign request interceptor. */
@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
  private final OAuth2RestTemplate oAuth2RestTemplate;

  /**
   * Instantiates a new O auth 2 feign request interceptor.
   *
   * @param oAuth2RestTemplate the o auth 2 rest template
   */
  OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
    Assert.notNull(oAuth2RestTemplate, "Context can not be null");
    this.oAuth2RestTemplate = oAuth2RestTemplate;
  }

  /**
   * Apply.
   *
   * @param template the template
   */
  @Override
  public void apply(RequestTemplate template) {
    log.debug(
        "Constructing Header {} for Token {}",
        HttpHeaders.AUTHORIZATION,
        GlobalConstant.HttpConfig.AUTH_HEADER_SPLIT);
    template.header(
        HttpHeaders.AUTHORIZATION,
        String.format(
            "%s %s",
            GlobalConstant.HttpConfig.AUTH_HEADER_SPLIT,
            oAuth2RestTemplate.getAccessToken().toString()));
  }
}
