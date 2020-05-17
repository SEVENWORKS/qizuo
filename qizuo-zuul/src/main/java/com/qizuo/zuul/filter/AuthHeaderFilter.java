/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.http.RequestUtil;
import com.qizuo.base.exception.BusinessException;
import com.qizuo.zuul.CoreHeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤器，路由调用之前鉴权
 */
@Slf4j
@Component
public class AuthHeaderFilter extends ZuulFilter {
    //header判断标识
    private static final String BEARER_TOKEN_TYPE = "bearer ";
    //uri判断标识
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";
    private static final String LOGOUT_URI = "/oauth/token";
    private static final String ALIPAY_CALL_URI = "/pay/alipayCallback";


    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。
     */
    @Override
    public Object run() {
        log.info("AuthHeaderFilter - 开始鉴权...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            //鉴权
            doSomething(requestContext);
        } catch (Exception e) {
            //鉴权失败
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
        //请求路径判断
        String requestURI = request.getRequestURI();
        if (OPTIONS.equalsIgnoreCase(request.getMethod()) || !requestURI.contains(AUTH_PATH) || !requestURI.contains(LOGOUT_URI) || !requestURI.contains(ALIPAY_CALL_URI)) {
            return;
        }

        //请求头判断
        String authHeader = RequestUtil.getAuthHeader(request);
        if (ObjectIsEmptyUtils.isEmpty(authHeader)) {
            throw new ZuulException("刷新页面重试", 403, "check token fail");
        }
        if (authHeader.startsWith(BEARER_TOKEN_TYPE)) {
            //添加请求头
            requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
            log.info("authHeader={} ", authHeader);
            // 传递给后续微服务
            requestContext.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_LABEL, authHeader);
        }
    }

}
