/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.authorizationServer.doResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.service.UserService;
import com.qizuo.security.model.SecurityUser;
import com.qizuo.util.http.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 认证成功处理器. */
@Component("AuthenticationSuccessHandler")
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Resource private ObjectMapper objectMapper;
  @Resource private ClientDetailsService clientDetailsService;
  @Resource private UserService userService;
  //  @Resource private AuthorizationServerTokenServices authorizationServerTokenServices;

  private static final String BEARER_TOKEN_TYPE = "Basic ";

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    logger.info("登录成功");
    // 判断是否有client信息
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
      throw new UnapprovedClientAuthenticationException("请求头中无client信息");
    }

    // 解析
    String[] tokens = RequestUtil.extractAndDecodeHeader(header);
    assert tokens.length == 2;
    String clientId = tokens[0];
    String clientSecret = tokens[1];
    ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
    if (clientDetails == null) {
      throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
    } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
      throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
    }

    // 返回token
    TokenRequest tokenRequest =
        new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
    OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
    OAuth2Authentication oAuth2Authentication =
        new OAuth2Authentication(oAuth2Request, authentication);
    OAuth2AccessToken token = new DefaultTokenServices().createAccessToken(oAuth2Authentication);
    SecurityUser principal = (SecurityUser) authentication.getPrincipal();

    // 数据库记录用户登录
    //    userService.handlerLoginData(token, principal, request);
    log.info("用户【 {} 】记录登录日志", principal.getUsername());
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write((objectMapper.writeValueAsString(BackResultUtils.ok(token))));
  }
}
