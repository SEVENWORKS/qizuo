/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.resourceServer.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** 用来解决匿名用户访问无权限资源时的异常 */
@Slf4j
@Configuration
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
  @Resource private ObjectMapper objectMapper;

  /**
   * Handle.
   *
   * @param request the request
   * @param response the response
   * @throws JsonProcessingException the json processing exception
   */
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    log.info("处理权限异常. e={}", authException);
    Map<String, Object> result = new HashMap<>(3);
    result.put("code", -1);
    result.put("message", "没有权限访问");
    String json = objectMapper.writeValueAsString(result);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(json);
  }
}
