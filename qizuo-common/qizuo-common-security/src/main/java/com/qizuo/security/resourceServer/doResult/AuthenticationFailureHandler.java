/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.security.resourceServer.doResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qizuo.base.utils.BackResultUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 认证错误时候的处理 */
@Component("AuthenticationFailureHandler")
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Resource private ObjectMapper objectMapper;

  /**
   * On authentication failure.
   *
   * @param request the request
   * @param response the response
   * @param exception the exception
   * @throws IOException the io exception
   * @throws ServletException the servlet exception
   */
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    logger.info("登录失败");
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setContentType("application/json;charset=UTF-8");
    response
        .getWriter()
        .write(objectMapper.writeValueAsString(BackResultUtils.error(exception.getMessage())));
  }
}
