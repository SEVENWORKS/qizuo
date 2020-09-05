/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.exceptions;

import com.qizuo.base.exception.BusinessException;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;


/**
 * user exception.
 */
@Slf4j
public class UserAdminException extends BusinessException {

	private static final long serialVersionUID = -6552248511084911254L;

	/**
	 * Instantiates a new Uac rpc exception.
	 */
	public UserAdminException() {
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param code      the code
	 * @param msgFormat the msg format
	 * @param args      the args
	 */
	public UserAdminException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		log.info("<== UserException, code:{}, message:{}", this.code, super.getMessage());
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public UserAdminException(int code, String msg) {
		super(code, msg);
		log.info("<== UserException, code:{}, message:{}", this.code, super.getMessage());
	}

	/**
	 * Instantiates a new Mdc rpc exception.
	 *
	 * @param codeEnum the code enum
	 */
	public UserAdminException(ResultCodeEnum codeEnum) {
		super(codeEnum.code(), codeEnum.msg());
		log.info("<== UserException, code:{}, message:{}", this.code, super.getMessage());
	}

	/**
	 * Instantiates a new Mdc rpc exception.
	 *
	 * @param codeEnum the code enum
	 * @param args     the args
	 */
	public UserAdminException(ResultCodeEnum codeEnum, Object... args) {
		super(codeEnum, args);
		log.info("<== UserException, code:{}, message:{}", this.code, super.getMessage());
	}
}
