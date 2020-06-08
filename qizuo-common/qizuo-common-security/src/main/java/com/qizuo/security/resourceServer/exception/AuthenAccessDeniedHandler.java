/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.resourceServer.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring默认的AccessDeniedHandler中只有对页面请求的处理，而没有对Ajax的处理。而在项目开发是Ajax又是我们要常用的技术，所以我们可以通过自定义AccessDeniedHandler来处理Ajax请求。我们在Spring默认的AccessDeniedHandlerImpl上稍作修改就可以了。
 * 用来解决认证过的用户访问无权限资源时的异常
 */
@Slf4j
@Configuration
public class AuthenAccessDeniedHandler implements AccessDeniedHandler {
  @Resource private ObjectMapper objectMapper;

  /**
   * Handle.
   *
   * @param request the request
   * @param response the response
   * @param e the e
   * @throws JsonProcessingException the json processing exception
   */
  @Override
  public void handle(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
      throws IOException {
    log.info("处理权限异常. e={}", e);
    Map<String, Object> result = new HashMap<>(3);
    result.put("code", -1);
    result.put("message", "没有资源访问");
    String json = objectMapper.writeValueAsString(result);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(json);
  }
}
