/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.security.service;

import lombok.Data;

/** clientservice勾心构造配置 */
@Data
public class RestClientProperties {

  /** 第三方应用appId */
  private String clientId;
  /** 第三方应用appSecret */
  private String clientSecret;
  /** 针对此应用发出的token的有效时间 */
  private int accessTokenValidateSeconds = 7200;
  /** 针对此应用刷新的token的有效时间 */
  private int refreshTokenValiditySeconds = 2592000;

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
