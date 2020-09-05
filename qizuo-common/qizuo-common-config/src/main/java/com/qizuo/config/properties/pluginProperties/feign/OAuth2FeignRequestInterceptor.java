/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.pluginProperties.feign;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Assert;

/** feign request interceptor. */
@Slf4j
@RefreshScope
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
  @Value("${token_rules}")
  private String tokenRules;

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
   * Apply.这个地方主要解决用了spring security oauth2之后，这边feign没有token401的问题<br>
   * 这个地方原来方案是重新建立个security请求器，再去请求一边这个token,这个有它的好处，不会有失效，但是比较复杂<br>
   * 现在新方案是直接取security的上下文，用它来获取现在已经存在token，再去给他重新装配再header上
   *
   * @param template the template
   */
  @Override
  public void apply(RequestTemplate template) {
    // 获取上下文
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if (authentication != null
        && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
      OAuth2AuthenticationDetails oAuth2AuthenticationDetails =
          (OAuth2AuthenticationDetails) authentication.getDetails();
      // template重新装配header
      log.debug(
          "Constructing Header {} for Token {}",
          HttpHeaders.AUTHORIZATION,
          GlobalConstant.HttpConfig.AUTH_HEADER_SPLIT);
      // zuul header
      template.header(tokenRules, GlobalConstant.Global);
      // token header
      template.header(
          HttpHeaders.AUTHORIZATION,
          String.format(
              "%s%s",
              GlobalConstant.HttpConfig.AUTH_HEADER_SPLIT,
              oAuth2AuthenticationDetails
                  .getTokenValue())); // 原来方案这里是有区别的oAuth2RestTemplate.getAccessToken().toString()
    }
  }
}
