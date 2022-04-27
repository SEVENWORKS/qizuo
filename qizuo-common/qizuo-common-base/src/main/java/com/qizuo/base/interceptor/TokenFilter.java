package com.qizuo.base.interceptor;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 和TokenInterceptor所作事情一样，主要拦截非zuul过来请求，为什么要filter实现一遍了？
 * filter是线性的，一个个执行下去，再到servlet，再返回filter；所以security的filter肯定在springmvc的handler之前(springmvc基于servlet，它的handler都是反射aop)
 * 所以不管怎么设置优先级都不可以，自定义filter
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class TokenFilter implements Filter{
    @Value("${token_rules}")
    private String tokenRules;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String zuulHeader = request.getHeader(tokenRules);
        if (ObjectIsEmptyUtils.isEmpty(zuulHeader)&&!uri.contains(GlobalConstant.Url$Path.TokenInterceptor_AUTH_PATH)){
            log.error("请求错误，不是路由的请求");
            //请求转发(转发request重定向response)
            request.getRequestDispatcher("/error").forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
