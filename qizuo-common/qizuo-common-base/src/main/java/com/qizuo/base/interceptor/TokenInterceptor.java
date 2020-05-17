/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.interceptor;

import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.model.auth.TokenDto;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.util.Thread.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * spring拦截器，验证token
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
	//redis操作对象
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 整个请求被调用，渲染视图后.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
		//如果有错误
		if (ex != null) {
			log.error("<== afterCompletion - 解析token失败. ex={}", ex.getMessage(), ex);
			//对返回请求进行处理
			this.handleException(response);
		}
	}
	private void handleException(HttpServletResponse res) throws IOException {
		res.resetBuffer();
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write("{\"code\":"+ ResultCodeEnum.UAC10010002.code()+" ,\"message\" :\""+ResultCodeEnum.UAC10010002.msg() +"\"}");
		res.flushBuffer();
	}

	/**
	 * 请求处理后.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
	}

	/**
	 * 请求进入前.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		//获取请求uri
		String uri = request.getRequestURI();
		log.info("<== preHandle - 权限拦截器.  url={}", uri);
		//当路径包含以上是不会走权限验证的
		if (uri.contains(GlobalConstant.Url$Path.TokenInterceptor_AUTH_PATH)) {
			log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
			return true;
		}

		//如果包含不需要验证的注解，也是不走token验证方法
		if (isHaveAccess(handler)) {
			log.info("<== preHandle - 不需要认证注解不走认证.  token={}");
			return true;
		}

		//下面是token验证
		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
		log.info("<== preHandle - 权限拦截器.  token={}", token);
		TokenDto loginUser = (TokenDto) redisTemplate.opsForValue().get('1');
		if (loginUser == null) {
			log.error("获取用户信息失败, 不允许操作");
			return false;
		}
		log.info("<== preHandle - 权限拦截器.  loginUser={}", loginUser);
		ThreadLocalMap.put(GlobalConstant.Role.SUPER, loginUser);
		log.info("<== preHandle - 权限拦截器.  url={}, loginUser={}", uri, loginUser);

		return true;
	}

	//判断是否存在不需要验证的注解
	private boolean isHaveAccess(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		NoNeedAccessAuthentication responseBody = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);
		return responseBody != null;
	}

}
  