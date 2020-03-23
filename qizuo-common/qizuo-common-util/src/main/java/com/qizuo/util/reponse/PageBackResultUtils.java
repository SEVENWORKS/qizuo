/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.reponse;


import com.qizuo.base.model.page.PageVo;
import com.qizuo.base.model.result.PageBackResult;

/**
 * reponse返回类工具for page.
 */
public class PageBackResultUtils {

	private PageBackResultUtils() {
	}

	private static <E> PageBackResult<E> wrap(int code, String message, E o, PageVo pageUtil) {
		return new PageBackResult<E>(code, message, o, pageUtil);
	}

	/**
	 * Wrap data with default code=200.
	 *
	 * @param <E>      the type parameter
	 * @param o        the o
	 * @param pageUtil the page util
	 *
	 * @return the page wrapper
	 */
	public static <E> PageBackResult<E> wrap(E o, PageVo pageUtil) {
		return wrap(PageBackResult.SUCCESS_CODE, PageBackResult.SUCCESS_MESSAGE, o, pageUtil);
	}

	/**
	 * Wrap.
	 *
	 * @param <E>     the type parameter
	 * @param code    the code
	 * @param message the message
	 *
	 * @return the page wrapper
	 */
	public static <E> PageBackResult<E> wrap(int code, String message) {
		return wrap(code, message, null, null);
	}

	/**
	 * Wrap.
	 *
	 * @param <E>  the type parameter
	 * @param code the code
	 *
	 * @return the page wrapper
	 */
	public static <E> PageBackResult<E> wrap(int code) {
		return wrap(code, null, null, null);
	}

	/**
	 * Wrap.
	 *
	 * @param <E> the type parameter
	 * @param e   the e
	 *
	 * @return the page wrapper
	 */
	public static <E> PageBackResult<E> wrap(Exception e) {
		return new PageBackResult<E>(PageBackResult.ERROR_CODE, e.getMessage(), null, null);
	}

	/**
	 * Un wrapper.
	 *
	 * @param <E>     the type parameter
	 * @param wrapper the wrapper
	 *
	 * @return the e
	 */
	public static <E> E unWrap(PageBackResult<E> wrapper) {
		return wrapper.getResult();
	}

	/**
	 * Wrap ERROR. code=500
	 *
	 * @param <E> the type parameter
	 *
	 * @return the page wrapper
	 */
	public static <E> PageBackResult<E> error() {
		return wrap(PageBackResult.ERROR_CODE, PageBackResult.ERROR_MESSAGE, null, null);
	}

	/**
	 * Wrap SUCCESS. code=200
	 *
	 * @param <E> the type parameter
	 *
	 * @return the page wrapper
	 */
	public static <E> PageBackResult<E> ok() {
		return new PageBackResult<E>();
	}
}
