/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class Core header interceptor.
 */
@Slf4j
public class CoreHeaderInterceptor extends HandlerInterceptorAdapter {
	/**
	 * The constant HEADER_LABEL.
	 */
	public static final String HEADER_LABEL = "x-qizuo";
	/**
	 * The constant HEADER_LABEL_SPLIT.
	 */
	public static final String HEADER_LABEL_SPLIT = ",";

	/**
	 * 跨线程存储器.
	 */
	public static final HystrixRequestVariableDefault<List<String>> LABEL = new HystrixRequestVariableDefault<>();

	/**
	 * 执行方法前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		//获取标志性请求头，然后进行处理
		CoreHeaderInterceptor.initHystrixRequestContext(request.getHeader(CoreHeaderInterceptor.HEADER_LABEL));
		return true;
	}

	//存储数据
	private static void initHystrixRequestContext(String labels) {
		log.info("LABEL={}", labels);
		//判断当前Hystrix是否初始化，没有初始化就初始化
		if (!HystrixRequestContext.isCurrentThreadInitialized()) {
			HystrixRequestContext.initializeContext();
		}
		//如果标志性请求头不为空，则进行线程set,存放一个list
		if (!StringUtils.isEmpty(labels)) {
			CoreHeaderInterceptor.LABEL.set(Arrays.asList(labels.split(CoreHeaderInterceptor.HEADER_LABEL_SPLIT)));
		} else {
			//否则线程清空
			CoreHeaderInterceptor.LABEL.set(Collections.emptyList());
		}
	}

	/**
	 * 方法处理后
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		//关闭线程
		CoreHeaderInterceptor.shutdownHystrixRequestContext();
	}

	//销毁存储器
	private static void shutdownHystrixRequestContext() {
		if (HystrixRequestContext.isCurrentThreadInitialized()) {
			HystrixRequestContext.getContextForCurrentThread().shutdown();
		}
	}
}
