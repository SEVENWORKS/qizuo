/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.reponse;

import com.qizuo.base.model.result.BackResult;
import org.apache.commons.lang3.StringUtils;

/**
 * response返回对象工具.
 */
public class BackResultUtils {

	private BackResultUtils() {
	}

	/**
	 * Wrap.
	 *
	 * @param <E>     the element type
	 * @param code    the code
	 * @param message the message
	 * @param o       the o
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> wrap(int code, String message, E o) {
		return new BackResult<>(code, message, o);
	}

	/**
	 * Wrap.
	 *
	 * @param <E>     the element type
	 * @param code    the code
	 * @param message the message
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> wrap(int code, String message) {
		return wrap(code, message, null);
	}

	/**
	 * Wrap.
	 *
	 * @param <E>  the element type
	 * @param code the code
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> wrap(int code) {
		return wrap(code, null);
	}

	/**
	 * Wrap.
	 *
	 * @param <E> the element type
	 * @param e   the e
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> wrap(Exception e) {
		return new BackResult<>(BackResult.ERROR_CODE, e.getMessage());
	}

	/**
	 * Un wrapper.
	 *
	 * @param <E>     the element type
	 * @param wrapper the wrapper
	 *
	 * @return the e
	 */
	public static <E> E unWrap(BackResult<E> wrapper) {
		return wrapper.getResult();
	}

	/**
	 * Wrap ERROR. code=500
	 *
	 * @param <E> the element type
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> error() {
		return wrap(BackResult.ERROR_CODE, BackResult.ERROR_MESSAGE);
	}


	/**
	 * Error wrapper.
	 *
	 * @param <E>     the type parameter
	 * @param message the message
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> error(String message) {
		return wrap(BackResult.ERROR_CODE, StringUtils.isBlank(message) ? BackResult.ERROR_MESSAGE : message);
	}

	/**
	 * Wrap SUCCESS. code=200
	 *
	 * @param <E> the element type
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> ok() {
		return new BackResult<>();
	}

	/**
	 * Ok wrapper.
	 *
	 * @param <E> the type parameter
	 * @param o   the o
	 *
	 * @return the wrapper
	 */
	public static <E> BackResult<E> ok(E o) {
		return new BackResult<>(BackResult.SUCCESS_CODE, BackResult.SUCCESS_MESSAGE, o);
	}
}
