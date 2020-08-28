/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.utils.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/** 向jwt中添加额外信息. */
public class TokenJwtEnhancer implements TokenEnhancer {

  /**
   * Enhance o auth 2 access token.
   *
   * @param accessToken the access token
   * @param oAuth2Authentication the o auth 2 authentication
   * @return the o auth 2 access token
   */
  @Override
  public OAuth2AccessToken enhance(
      OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
    Map<String, Object> info = new HashMap<>(8);

    // 额外信息添加
    info.put("timestamp", System.currentTimeMillis());
    Authentication authentication = oAuth2Authentication.getUserAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      Object principal = authentication.getPrincipal();
      info.put("loginName", ((UserDetails) principal).getUsername());
    }

    // 放入token中
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

    return accessToken;
  }
}
