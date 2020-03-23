/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.result;


import com.qizuo.base.model.page.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回分页实体类.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageBackResult<T> extends BackResult<T> {

	private static final long serialVersionUID = 666985064788933395L;

	/**
	 * 分页基本实体类(比普通result返回多了这个)
	 */
	private PageVo pageVo;

	public PageBackResult() {
		super();
	}

	public PageBackResult(int code, String message) {
		super(code, message);
	}

	public PageBackResult(T result, PageVo pageVo) {
		super();
		this.setResult(result);
		this.setPageVo(pageVo);
	}

	public PageBackResult(int code, String message, T result, PageVo pageVo) {
		super(code, message, result);
		this.pageVo = pageVo;
	}

	/**
	 * Sets the 分页数据 , 返回自身的引用.
	 *
	 * @param pageVo the page util
	 *
	 * @return the page wrapper
	 */
	public PageBackResult<T> pageVo(PageVo pageVo) {
		this.setPageVo(pageVo);
		return this;
	}

	/**
	 * Sets the 结果数据 , 返回自身的引用.
	 *
	 * @param result the result
	 *
	 * @return the page wrapper
	 */
	@Override
	public PageBackResult<T> result(T result) {
		super.setResult(result);
		return this;
	}
}
