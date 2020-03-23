/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * response的时候添加header标志.这个和coreheader是并行的
 */
@Slf4j
public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	/**
	 * response的时候添加header.
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
	                                    ClientHttpRequestExecution execution) throws IOException {
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		//把header集合变成指定字符串
		String header = StringUtils.collectionToDelimitedString(
				CoreHeaderInterceptor.LABEL.get(),
				CoreHeaderInterceptor.HEADER_LABEL_SPLIT);
		log.info("header={} ", header);
		//添加一个header
		requestWrapper.getHeaders().add(CoreHeaderInterceptor.HEADER_LABEL, header);
		return execution.execute(requestWrapper, body);
	}
}
