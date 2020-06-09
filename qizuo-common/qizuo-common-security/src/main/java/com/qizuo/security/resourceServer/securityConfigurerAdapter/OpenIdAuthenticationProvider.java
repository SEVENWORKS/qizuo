/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.resourceServer.securityConfigurerAdapter;

import com.qizuo.security.service.SecurityUserDetailsSevice;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 自定义身份认证 认证是由 AuthenticationManager 来管理的，但是真正进行认证的是 AuthenticationManager 中定义的
 * AuthenticationProvider。AuthenticationManager 中可以定义有多个 AuthenticationProvider.
 */
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

  private SecurityUserDetailsSevice userDetailsService;

  /**
   * Authenticate authentication. 进行认证
   *
   * @param authentication the authentication
   * @return the authentication
   * @throws AuthenticationException the authentication exception
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // 转换之前在filter中塞入的值
    OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;

    // 获取用户信息
    UserDetails user =
        userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
    if (user == null) {
      throw new InternalAuthenticationServiceException("无法获取用户信息");
    }

    // 重新构建token
    OpenIdAuthenticationToken authenticationResult =
        new OpenIdAuthenticationToken(user, user.getAuthorities());
    authenticationResult.setDetails(authenticationToken.getDetails());

    return authenticationResult;
  }

  /**
   * Supports boolean.
   *
   * @param authentication the authentication
   * @return the boolean
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
  }

  /**
   * Gets user details service.
   *
   * @return the user details service
   */
  public SecurityUserDetailsSevice getUserDetailsService() {
    return userDetailsService;
  }

  /**
   * Sets user details service.
   *
   * @param userDetailsService the user details service
   */
  public void setUserDetailsService(SecurityUserDetailsSevice userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
}
