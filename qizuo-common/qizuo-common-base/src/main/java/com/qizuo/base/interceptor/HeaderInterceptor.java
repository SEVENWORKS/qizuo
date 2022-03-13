/*
 *Copyright(c)2020.
 *author：qizuo
 */
package com.qizuo.base.interceptor;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** springmvc拦截所有的请求并做相应的处理,利用拦截器拦截请求或者认证请求头，并存储到跨线程存储器中方便后面取用 TODO 暂时没用*/
@Slf4j
@Component
public class HeaderInterceptor extends HandlerInterceptorAdapter {
  /** 跨线程存储器 存储前初始化. 用于存储 request level 的数据。与此相对的是 thread level的数据，一般用ThreadLocal来处理 */
  // request level context的意义，无论是哪个线程在处理，只要在当前请求的生命周期内，都可以拿到request的数据
  public static final HystrixRequestVariableDefault<List<String>> LABEL =
      new HystrixRequestVariableDefault<>();

  /** 执行方法前 */
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 获取标志性请求头，然后进行处理
    HeaderInterceptor.initHystrixRequestContext(
        request.getHeader(GlobalConstant.HttpConfig.HEADER_ZUUL));
    return true;
  }

  // 存储数据
  private static void initHystrixRequestContext(String labels) {
    log.info("LABEL={}", labels);
    // 判断当前Hystrix是否初始化，没有初始化就初始化 即在当前线程下创建一个HystrixRequestContext对象
    if (!HystrixRequestContext.isCurrentThreadInitialized()) {
      HystrixRequestContext.initializeContext();
    }
    // 如果标志性请求头不为空，则进行线程set,存放一个list
    if (!StringUtils.isEmpty(labels)) {
      HeaderInterceptor.LABEL.set(Arrays.asList(labels.split(GlobalConstant.Symbol.COMMA)));
    } else {
      // 否则线程清空
      HeaderInterceptor.LABEL.set(Collections.emptyList());
    }
  }

  /** 方法处理后 */
  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {
    // 关闭线程
    HeaderInterceptor.shutdownHystrixRequestContext();
  }

  // 销毁存储器
  private static void shutdownHystrixRequestContext() {
    if (HystrixRequestContext.isCurrentThreadInitialized()) {
      HystrixRequestContext.getContextForCurrentThread().shutdown();
    }
  }
}
