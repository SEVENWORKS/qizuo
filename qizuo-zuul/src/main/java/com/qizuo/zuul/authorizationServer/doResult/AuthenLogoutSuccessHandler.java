/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.zuul.authorizationServer.doResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qizuo.base.utils.BackResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 退出成功处理器，返回json格式的响应。 TODO 暂时不用*/
@Slf4j
public class AuthenLogoutSuccessHandler implements LogoutSuccessHandler {

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * On logout success.
   *
   * @param request the request
   * @param response the response
   * @param authentication the authentication
   * @throws IOException the io exception
   */
  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    log.info("退出成功");
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(BackResultUtils.ok("退出成功")));
  }
}
