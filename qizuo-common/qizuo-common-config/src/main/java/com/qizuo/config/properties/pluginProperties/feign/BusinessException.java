/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.pluginProperties.feign;


import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务异常.
 */
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
@Slf4j
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 3160241586346324994L;

	/**
	 * 异常码
	 */
	protected int code;


	public BusinessException() {
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}

	public BusinessException(ResultCodeEnum codeEnum, Object... args) {
		super(String.format(codeEnum.msg(), args));
		this.code = codeEnum.code();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
