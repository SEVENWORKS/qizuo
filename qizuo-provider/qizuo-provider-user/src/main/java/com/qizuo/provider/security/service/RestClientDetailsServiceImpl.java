/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/** The class Rest client details service. */
@Slf4j
@Component("restClientDetailsService")
public class RestClientDetailsServiceImpl implements ClientDetailsService {
  private ClientDetailsService clientDetailsService;
  private OAuth2ClientProperties[] clients = {};

  /**
   * 1@PostConstruct该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
   */
  @PostConstruct
  public void init() {
    // 重新构造一个clientDetailsService
    InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
    if (ArrayUtils.isNotEmpty(clients)) {
      for (OAuth2ClientProperties client : clients) {
        builder
            .withClient(client.getClientId())
            .secret(client.getClientSecret())
            .authorizedGrantTypes("refresh_token", "password", "client_credentials")
            .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
            .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
            .scopes(client.getScope());
      }
    }
    try {
      clientDetailsService = builder.build();
    } catch (Exception e) {
      log.error("init={}", e.getMessage(), e);
    }
  }

  /**
   * Load client by client id client details.
   *
   * @param clientId the client id
   * @return the client details
   * @throws ClientRegistrationException the client registration exception
   */
  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    return clientDetailsService.loadClientByClientId(clientId);
  }
}
