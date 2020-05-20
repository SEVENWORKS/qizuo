/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *出现异常的时候，zuul进行fallback
 */
//普通pojo放到spring容器中
//@Component
//不用每次单独定义一个Log，利用这个注解加上lombok插件，可以直接在下面类中使用log进行日志打印
@Slf4j
public class UacFallbackProvider implements ZuulFallbackProvider {

	//微服务配了路由的话，就用配置的名称
	//return "customers";
	//如果要为所有路由提供默认回退，可以创建FallbackProvider类型的bean并使getRoute方法返回*或null
	//return "*";
	@Override
	public String getRoute() {
		return "*";
	}

	/**
	 * 第一道自定义返回值来处理错误请求
	 * @param cause
	 * @return
	 */
	public ClientHttpResponse fallbackResponse(final Throwable cause) {
		if (cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else {
			return fallbackResponse();
		}
	}

	/**
	 * 第二道自定义返回值来处理错误请求
	 * @return
	 */
	@Override
	public ClientHttpResponse fallbackResponse() {
		return response(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 第三道设定不同的返回情景
	 * @param status
	 * @return
	 */
	private ClientHttpResponse response(final HttpStatus status) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() {
				return status;
			}

			@Override
			public int getRawStatusCode() {
				return status.value();
			}

			@Override
			public String getStatusText() {
				return status.getReasonPhrase();
			}

			@Override
			public void close() {
				log.info("close");
			}

			@Override
			public InputStream getBody() {
				String message = "{\n" +
						"\"code\": 200,\n" +
						"\"message\": \"微服务故障, 请稍后再试\"\n" +
						"}";
				return new ByteArrayInputStream(message.getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}
}
