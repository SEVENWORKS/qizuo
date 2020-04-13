package com.qizuo.util.spring;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: fangl
 * @description: spring常用工具类
 * @date: 16:40 2020/4/11
 */
public class SpringUtils {
    /**
     * @author: fangl
     * @description: 根据springmvc过滤器获取request
     * @date: 13:24 2019/6/2
     */
    public static HttpServletRequest qHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * @author: fangl
     * @description: 根据springmvc过滤器获取reSponse
     * @date: 13:24 2019/6/2
     */
    public static HttpServletResponse qHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
