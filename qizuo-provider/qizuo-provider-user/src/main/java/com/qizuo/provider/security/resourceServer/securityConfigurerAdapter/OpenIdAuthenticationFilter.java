/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.resourceServer.securityConfigurerAdapter;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractAuthenticationProcessingFilter的职责也就非常明确——处理所有HTTP
 * Request和Response对象，并将其封装成AuthenticationMananger可以处理的Authentication。并且在身份验证成功或失败之后将对应的行为转换为HTTP的Response。同时还要处理一些Web特有的资源比如Session和Cookie。总结成一句话，就是替AuthenticationMananger把所有和Authentication没关系的事情全部给包圆了。
 */
@Data
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  private static final String openIdParameter = "openId";
  private static final String providerIdParameter = "providerId";
  private static final boolean postOnly = true;
  private static final String POST = "POST";
  private static final String patternFilter = "/auth/openid";

  /** Instantiates a new Open id authentication filter. */
  OpenIdAuthenticationFilter() {
    super(new AntPathRequestMatcher(patternFilter, POST));
  }

  /**
   * Attempt authentication authentication. 构建认证体
   *
   * @param request the request
   * @param response the response
   * @return the authentication
   * @throws AuthenticationException the authentication exception
   */
  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    // 判断请求方式是否是post
    if (postOnly && !POST.equals(request.getMethod())) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }

    // 判断heder中参数
    String openid = obtainOpenId(request);
    String providerId = obtainProviderId(request);
    if (openid == null) {
      openid = "";
    }
    if (providerId == null) {
      providerId = "";
    }
    openid = openid.trim();
    providerId = providerId.trim();

    // 构建新的token
    OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openid, providerId);

    // 塞入值
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

    return this.getAuthenticationManager().authenticate(authRequest);
  }

  /**
   * 获取openId
   *
   * @param request the request
   * @return the string
   */
  protected String obtainOpenId(HttpServletRequest request) {
    return request.getParameter(openIdParameter);
  }

  /**
   * 获取提供商id
   *
   * @param request the request
   * @return the string
   */
  protected String obtainProviderId(HttpServletRequest request) {
    return request.getParameter(providerIdParameter);
  }
}
