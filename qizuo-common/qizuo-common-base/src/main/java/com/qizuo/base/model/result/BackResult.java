/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;


/**
 * 返回实体类
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BackResult<T> implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 4893280118017319089L;

	/**
	 * 默认成功码.
	 */
	public static final int SUCCESS_CODE = ResultCodeEnum.GLSUCCESS.code();

	/**
	 * 成功信息.
	 */
	public static final String SUCCESS_MESSAGE = ResultCodeEnum.GLSUCCESS.msg();

	/**
	 * 默认错误码.
	 */
	public static final int ERROR_CODE = ResultCodeEnum.GLERROR.code();

	/**
	 * 错误信息.
	 */
	public static final String ERROR_MESSAGE = ResultCodeEnum.GLERROR.msg();

	/**
	 * 编号.
	 */
	private int code;

	/**
	 * 信息.
	 */
	private String message;

	/**
	 * 结果数据
	 */
	private T result;

	/**
	 * 默认成功
	 */
	public BackResult() {
		this(SUCCESS_CODE, SUCCESS_MESSAGE);
	}

	/**
	 * Instantiates a new wrapper.
	 *
	 * @param code    the code
	 * @param message the message
	 */
	public BackResult(int code, String message) {
		this(code, message, null);
	}

	/**
	 * Instantiates a new wrapper.
	 *
	 * @param code    the code
	 * @param message the message
	 * @param result  the result
	 */
	public  BackResult(int code, String message, T result) {
		super();
		this.code(code).message(message).result(result);
	}

	/**
	 * Sets the 编号 , 返回自身的引用.
	 *
	 * @param code the new 编号
	 *
	 * @return the wrapper
	 */
	private BackResult<T> code(int code) {
		this.setCode(code);
		return this;
	}

	/**
	 * Sets the 信息 , 返回自身的引用.
	 *
	 * @param message the new 信息
	 *
	 * @return the wrapper
	 */
	private BackResult<T> message(String message) {
		this.setMessage(message);
		return this;
	}

	/**
	 * Sets the 结果数据 , 返回自身的引用.
	 *
	 * @param result the new 结果数据
	 *
	 * @return the wrapper
	 */
	public BackResult<T> result(T result) {
		this.setResult(result);
		return this;
	}

	/**
	 * 判断是否成功
	 */
	@JsonIgnore
	public boolean success() {
		return BackResult.SUCCESS_CODE == this.code;
	}

	/**
	 * 判断是否失败
	 */
	@JsonIgnore
	public boolean error() {
		return !success();
	}

}
