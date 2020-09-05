/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.interceptor;

import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.model.auth.UserDto;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.util.Thread.ThreadLocalMap;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.parse.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/** spring拦截器，验证token */
@Slf4j
@RefreshScope
public class TokenInterceptor implements HandlerInterceptor {
  @Value("${token_rules}")
  private String tokenRules;
  // token管理
  @Resource private TokenStore jwtTokenStore;
  // redis操作对象
  @Resource private RedisTemplate<String, Object> redisTemplate;

  /** 整个请求被调用，渲染视图后. */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex)
      throws Exception {
    // 如果有错误
    if (ex != null) {
      log.error("<== afterCompletion - 解析token失败. ex={}", ex.getMessage(), ex);
      // 对返回请求进行处理
      this.handleException(response);
    }
  }

  private void handleException(HttpServletResponse res) throws IOException {
    res.resetBuffer();
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Credentials", "true");
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    res.getWriter()
        .write(
            "{\"code\":"
                + ResultCodeEnum.UAC10010002.code()
                + " ,\"message\" :\""
                + ResultCodeEnum.UAC10010002.msg()
                + "\"}");
    res.flushBuffer();
  }

  /** 请求处理后. */
  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {}

  /** 请求进入前. */
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 请求头判断，如果不是从zuul触发的请求，都返回
    String zuulHeader = request.getHeader(tokenRules);
    if (ObjectIsEmptyUtils.isEmpty(zuulHeader)) {
      log.error("请求错误，不是路由的请求");
      return false;
    }

    // 获取请求uri
    String uri = request.getRequestURI();
    log.info("<== preHandle - 权限拦截器.  url={}", uri);
    // 当路径包含以上是不会走权限验证的,这地方error的直接放行给error处理
    if (uri.contains(GlobalConstant.Url$Path.TokenInterceptor_AUTH_PATH)
        || uri.contains(GlobalConstant.Url$Path.TokenInterceptor_SECURITY_PATH)
        || uri.contains(GlobalConstant.Url$Path.TokenInterceptor_SECURITY_PATH2)) {
      log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
      return true;
    }

    // 下面是token验证
    String token =
        StringUtils.substringAfter(
            request.getHeader(HttpHeaders.AUTHORIZATION),
            GlobalConstant.HttpConfig.AUTH_HEADER_SPLIT);
    log.info("<== preHandle - 权限拦截器.  token={}", token);
    // 破解token
    OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
    Boolean isExpired =
        ObjectIsEmptyUtils.isEmpty(oAuth2AccessToken) ? true : oAuth2AccessToken.isExpired();
    if (isExpired) {
      log.error("token过期或者无法获取token");
      return false;
    }
    // 存入token
    ThreadLocalMap.put(GlobalConstant.SafeCode.TOKEN, token);

    // 如果包含不需要验证的注解，即不需要User信息
    if (isHaveAccess(handler)) {
      log.info("<== preHandle - 不需要认证注解不走认证.  token={}");
      return true;
    }

    // 下面是user验证，顺便把user信息放入到localmap中
    UserDto user = null;
    try {
      user = JacksonUtil.parseJson((String) redisTemplate.opsForValue().get(token), UserDto.class);
    } catch (IOException e) {
      log.error("获取用户信息失败");
      return false;
    }
    if (ObjectIsEmptyUtils.isEmpty(user)) {
      log.error("获取用户信息失败");
      return false;
    }
    log.info("<== preHandle - 权限拦截器.  loginUser={}", user);
    ThreadLocalMap.put(GlobalConstant.Role.COMMON_USER, user);
    log.info("<== preHandle - 权限拦截器.  url={}, loginUser={}", uri, user);

    return true;
  }

  // 判断是否存在不需要验证的注解
  private boolean isHaveAccess(Object handler) {
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Method method = handlerMethod.getMethod();
    NoNeedAccessAuthentication responseBody =
        AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);
    return responseBody != null;
  }
}
