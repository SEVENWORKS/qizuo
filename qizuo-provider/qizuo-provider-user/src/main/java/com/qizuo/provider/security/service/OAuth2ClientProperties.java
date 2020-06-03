/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.service;

import lombok.Data;

/** clientservice勾心构造配置 */
@Data
public class OAuth2ClientProperties {

  /** 第三方应用appId */
  private String clientId;
  /** 第三方应用appSecret */
  private String clientSecret;
  /** 针对此应用发出的token的有效时间 */
  private int accessTokenValidateSeconds = 7200;

  private int refreshTokenValiditySeconds = 2592000;
  private String scope;
}
