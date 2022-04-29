/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.pluginProperties.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;


/**
 * Feign error interceptor.
 */
@Slf4j
public class Oauth2FeignErrorInterceptor implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder = new Default();

	/**
	 * Decode exception.
	 *
	 * @param methodKey the method key
	 * @param response  the response
	 *
	 * @return the exception
	 */
	@Override
	public Exception decode(final String methodKey, final Response response) {
		//不是对应错误码走下面，注意鉴权不过的也都走这个
		if (response.status() >= HttpStatus.BAD_REQUEST.value() && response.status() < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			return new HystrixBadRequestException("request exception wrapper");
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			HashMap map = mapper.readValue(response.body().asInputStream(), HashMap.class);
			Integer code = (Integer) map.get("code");
			String message = (String) map.get("message");
			if (code != null) {
				ResultCodeEnum anEnum = ResultCodeEnum.getEnum(code);
				if (anEnum != null) {
					if (anEnum == ResultCodeEnum.GL99990100) {
						return new IllegalArgumentException(message);
					} else {
						return new BusinessException(anEnum);
					}
				} else {
					return new BusinessException(ResultCodeEnum.GL99990500, message);
				}
			}
		} catch (IOException e) {
			log.info("Failed to process response body");
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}
}
