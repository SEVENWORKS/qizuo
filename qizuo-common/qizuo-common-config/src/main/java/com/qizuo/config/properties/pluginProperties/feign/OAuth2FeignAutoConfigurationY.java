/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.pluginProperties.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/** feign configuration core.核心，主要配置OAuth2FeignRequestInterceptor使用 */
@Configuration
@EnableConfigurationProperties(Oauth2ClientPropertiesY.class)
public class OAuth2FeignAutoConfigurationY {

  private final Oauth2ClientPropertiesY oauth2ClientProperties;

  /**
   * Instantiates a new O auth 2 feign auto configuration. 这个配置主要是认证bean的一些配置，包括token请求的一些配置
   *
   * @param oauth2ClientProperties the oauth 2 client properties
   */
  @Autowired
  public OAuth2FeignAutoConfigurationY(Oauth2ClientPropertiesY oauth2ClientProperties) {
    this.oauth2ClientProperties = oauth2ClientProperties;
  }

  /**
   * Resource details client credentials resource details. 这个认证bean主要是为了OAuth2RestTemplate构造
   *
   * @return the client credentials resource details
   */
  @Bean("clientCredentialsResourceDetails")
  public ClientCredentialsResourceDetails resourceDetails() {
    ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
    details.setId(oauth2ClientProperties.getId());
    details.setAccessTokenUri(oauth2ClientProperties.getAccessTokenUrl());
    details.setClientId(oauth2ClientProperties.getClientId());
    details.setClientSecret(oauth2ClientProperties.getClientSecret());
    //
    //	details.setAuthenticationScheme(AuthenticationScheme.valueOf(oauth2ClientProperties.getClientAuthenticationScheme()));
    return details;
  }

  /**
   * O auth 2 rest template o auth 2 rest template.这个template主要是为了feig请求拦截器使用
   *
   * @return the o auth 2 rest template
   */
  @Bean("oAuth2RestTemplate")
  public OAuth2RestTemplate oAuth2RestTemplate() {
    final OAuth2RestTemplate oAuth2RestTemplate =
        new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
    oAuth2RestTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());
    return oAuth2RestTemplate;
  }

  /**
   * Oauth 2 feign request interceptor request interceptor.<br>
   * 这里的feign请求拦截器主要是为了携带上token <br>
   * Qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的
   *
   * @param oAuth2RestTemplate the o auth 2 rest template
   * @return the request interceptor
   */
  @Bean
  public RequestInterceptor oauth2FeignRequestInterceptor(
      @Qualifier("oAuth2RestTemplate") OAuth2RestTemplate oAuth2RestTemplate) {
    return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
  }

  /**
   * Feign logger level logger . level.
   *
   * @return the logger . level
   */
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new Oauth2FeignErrorInterceptor();
  }
}
