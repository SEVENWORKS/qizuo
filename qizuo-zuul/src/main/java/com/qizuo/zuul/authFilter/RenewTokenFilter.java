/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.authFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.qizuo.base.exception.BusinessException;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** zuul过滤器，在route和error过滤器之后进行认证续租，主要是返回一个需要刷新token的Renew-Header,只要获取到这个header说明就需要刷新token了 */
@Component
@Slf4j
public class RenewTokenFilter extends ZuulFilter {
  // @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了
  // token管理
  @Resource private JwtTokenStore jwtTokenStore;
  // token过期时间
  private static final int EXPIRES_IN = 60 * 20;

  /**
   * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： pre：可以在请求被路由之前调用 route：在路由请求时候被调用
   * post：在route和error过滤器之后被调用 error：处理请求时发生错误时被调用
   */
  @Override
  public String filterType() {
    return "post";
  }

  /** 通过int值来定义过滤器的执行顺序 */
  @Override
  public int filterOrder() {
    return 10;
  }

  /** 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效 */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /** 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。 */
  @Override
  public Object run() {
    log.info("RenewFilter - token续租...");
    RequestContext requestContext = RequestContext.getCurrentContext();
    try {
      // 续租
      doSomething(requestContext);
    } catch (Exception e) {
      // 续租失败
      log.error("RenewFilter - token续租. [FAIL] EXCEPTION={}", e.getMessage(), e);
      throw new BusinessException(ResultCodeEnum.UAC10011041);
    }
    return null;
  }

  /**
   * 续租
   *
   * @param requestContext
   */
  private void doSomething(RequestContext requestContext) {
    HttpServletRequest request = requestContext.getRequest();
    // 获取token
    String token =
        StringUtils.substringAfter(
            request.getHeader(HttpHeaders.AUTHORIZATION),
            GlobalConstant.HttpConfig.AUTH_HEADER_SPLIT);
    if (StringUtils.isEmpty(token)) {
      return;
    }
    // 破解token
    OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
    // 获取过期时间
    int expiresIn = oAuth2AccessToken.getExpiresIn();
    // 判断时间是否过期，小于1200秒时候，就返回需要刷新token的信息
    if (expiresIn < EXPIRES_IN) {
      HttpServletResponse servletResponse = requestContext.getResponse();
      servletResponse.addHeader("Renew-Header", "true");
    }
  }
}
