/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.security.service;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
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
  /** 针对此应用发出的token的有效时间 */
  private int accessTokenValidateSeconds = 7200;
  /** 针对此应用刷新的token的有效时间 */
  private int refreshTokenValiditySeconds = 2592000;

  private ClientDetailsService clientDetailsService;

  /**
   * 1@PostConstruct该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
   */
  @PostConstruct
  public void init() {
    // 重新构造一个clientDetailsService
    InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
    // *针对不同的客户端要求不同的配置，这里的客户端指所有的调用服务
    builder
        .withClient(GlobalConstant.Global) // （必填）客户端ID
        .secret(GlobalConstant.Global) // (可信客户端需要）客户机密码（如果有）。没有可不填
        .authorizedGrantTypes("client_credentials", "password", "refresh_token") // **授予客户端使用授权的类型
        // 密码授权模式和刷新令牌,注意这个地方会限制客户端访问模式"authorization_code""implicit"
        .accessTokenValiditySeconds(GlobalConstant.SafeCode.TOKEN_TIME) // 授权码存活时间
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds) // //默认30天，这里修改
        .scopes("read"); // 客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
    builder
        .withClient(GlobalConstant.Global + GlobalConstant.Global) // （必填）客户端ID
        .secret(GlobalConstant.Global) // (可信客户端需要）客户机密码（如果有）。没有可不填
        .authorizedGrantTypes("authorization_code", "implicit", "refresh_token") // **授予客户端使用授权的类型
        // 密码授权模式和刷新令牌,注意这个地方会限制客户端访问模式"authorization_code""implicit"
        .accessTokenValiditySeconds(GlobalConstant.SafeCode.TOKEN_TIME) // 授权码存活时间
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds) // //默认30天，这里修改
        .scopes("write"); // 客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
    builder
        .withClient(
            GlobalConstant.Global + GlobalConstant.Global + GlobalConstant.Global) // （必填）客户端ID
        .secret(GlobalConstant.Global) // (可信客户端需要）客户机密码（如果有）。没有可不填
        .authorizedGrantTypes(
            "authorization_code", "implicit", "password", "refresh_token") // **授予客户端使用授权的类型
        // 密码授权模式和刷新令牌,注意这个地方会限制客户端访问模式"authorization_code""implicit"
        .accessTokenValiditySeconds(GlobalConstant.SafeCode.TOKEN_TIME) // 授权码存活时间
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds) // //默认30天，这里修改
        .scopes("all"); // 客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
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
