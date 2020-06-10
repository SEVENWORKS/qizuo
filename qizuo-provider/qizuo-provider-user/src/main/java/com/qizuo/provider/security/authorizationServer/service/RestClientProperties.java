/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.authorizationServer.service;

import lombok.Data;

/** 认证服务针对某种客户端的配置 */
@Data
public class RestClientProperties {

  /** 客户端标识应用appId */
  private String clientId;
  /** 客户端标识应用appSecret */
  private String clientSecret;
  /** 针对此应用发出的token的有效时间 */
  private int accessTokenValidateSeconds = 7200;
  /** 针对此应用刷新的token的有效时间 */
  private int refreshTokenValiditySeconds = 2592000;
  /** 该客户端能使用的范围 */
  private String scope;

  public RestClientProperties() {
    super();
  }

  public RestClientProperties(String clientId, String clientSecret, String scope) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scope = scope;
  }
}
