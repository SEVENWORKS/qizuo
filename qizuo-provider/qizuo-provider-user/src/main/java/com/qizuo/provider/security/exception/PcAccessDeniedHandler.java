/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：PcAccessDeniedHandler.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.qizuo.provider.security.exception;

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
 * @author paascloud.net @gmail.com
 */
@Slf4j
@Configuration
public class PcAccessDeniedHandler implements AccessDeniedHandler {
	@Resource
	private ObjectMapper objectMapper;

	/**
	 * Handle.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param e        the e
	 *
	 * @throws JsonProcessingException the json processing exception
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
		log.info("处理权限异常. e={}", e);
		Map<String, Object> result = new HashMap<>(3);
		result.put("code", 99990401);
		result.put("message", "无访问权限");
		String json = objectMapper.writeValueAsString(result);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}
}