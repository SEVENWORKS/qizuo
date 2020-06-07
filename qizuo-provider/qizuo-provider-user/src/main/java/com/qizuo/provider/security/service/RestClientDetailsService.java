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

/**
 * The class Rest client details service. 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
 * 注意这个和userDetailsservice不一样 这个是类似加载的配置项
 */
@Slf4j
@Component("restClientDetailsService")
public class RestClientDetailsService implements ClientDetailsService {
  private ClientDetailsService clientDetailsService;
  private RestClientProperties[] clients = {};

  /**
   * 1@PostConstruct该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
   */
  @PostConstruct
  public void init() {
    // 重新构造一个clientDetailsService
    InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
    if (ArrayUtils.isNotEmpty(clients)) {
      // *针对不同的客户端要求不同的配置，这里的客户端指所有的调用服务
      for (RestClientProperties client : clients) {
        builder
            .withClient(client.getClientId()) // （必填）客户端ID
            .secret(client.getClientSecret()) // (可信客户端需要）客户机密码（如果有）。没有可不填
            .authorizedGrantTypes(
                "refresh_token", "password", "client_credentials") // 授予客户端使用授权的类型 密码授权模式和刷新令牌
            .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds()) // 授权码存活时间
            .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds()) // //默认30天，这里修改
            .scopes(client.getScope()); // 客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
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
