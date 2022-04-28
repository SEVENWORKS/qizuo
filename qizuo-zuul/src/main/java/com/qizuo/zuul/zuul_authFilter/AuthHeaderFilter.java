/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.zuul_authFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qizuo.base.exception.BusinessException;
import com.qizuo.base.utils.PublicUtil;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.http.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/** zuul过滤器，请求头处理，主要是判断是否有鉴权的请求头和对鉴权的请求头进行延续，方便后面微服务可以取到 */
@Slf4j
@Component
@RefreshScope
public class AuthHeaderFilter extends ZuulFilter {
  @Value("${token_rules}")
  private String tokenRules;
  // uri判断标识
  private static final String OPTIONS = "OPTIONS";

  /**
   * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： pre：可以在请求被路由之前调用 route：在路由请求时候被调用
   * post：在route和error过滤器之后被调用 error：处理请求时发生错误时被调用
   */
  @Override
  public String filterType() {
    return "pre";
  }

  /** 通过int值来定义过滤器的执行顺序 */
  @Override
  public int filterOrder() {
    return 0;
  }

  /** 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效 */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /** 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。 */
  @Override
  public Object run() {
    log.info("AuthHeaderFilter - 开始鉴权...");
    RequestContext requestContext = RequestContext.getCurrentContext();
    try {
      // 鉴权
      doSomething(requestContext);
    } catch (Exception e) {
      // 鉴权失败
      log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
      throw new BusinessException(ResultCodeEnum.UAC10011041);
    }
    return null;
  }

  /**
   * 鉴权
   *
   * @param requestContext
   * @throws ZuulException
   */
  private void doSomething(RequestContext requestContext) throws ZuulException {
    HttpServletRequest request = requestContext.getRequest();
    // 一进来打上特殊标识
    requestContext.addZuulRequestHeader(tokenRules, GlobalConstant.Global);

    // 请求路径
    String requestURI = request.getRequestURI();

    // 请求头判断
    String authHeader = RequestUtil.getAuthHeader(request);
    // security的请求头为空
    if (ObjectIsEmptyUtils.isNotEmpty(authHeader)) {
      log.info("authHeader={} ", authHeader);
      // 传递给后续微服务
      // 添加请求头
      requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
      // 添加自主标识
      requestContext.addZuulRequestHeader(tokenRules, authHeader);
    } else if(!PublicUtil.isSpeUri(requestURI)||OPTIONS.equalsIgnoreCase(request.getMethod())) {
      // 正式跨域之前，浏览器会根据需要发起一次预检（也就是option请求），用来让服务端返回允许的方法（如get、post），被跨域访问的Origin（来源或者域），还有是否需要Credentials(认证信息)等,所以options请求需要去掉
      //这个路径特殊，第一次请求是不会有author请求头的，但是必须放行；第二次请求自带authore请求头，包含用户名密码信息等
      // zuulException
      throw new ZuulException("刷新页面重试", 403, "check token fail");
    }
  }
}
