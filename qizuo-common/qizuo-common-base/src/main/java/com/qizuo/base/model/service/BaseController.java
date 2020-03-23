/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.service;

import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.exception.BusinessException;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.Thread.ThreadLocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base controller.
 */
public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取当前登录人信息
	 */
	protected UserDto getLoginAuthDto() {
		UserDto loginAuthDto = (UserDto) ThreadLocalMap.get(GlobalConstant.Role.SUPER);
		if (ObjectIsEmptyUtils.isEmpty(loginAuthDto)) {
			throw new BusinessException(ResultCodeEnum.UAC10011041);
		}
		return loginAuthDto;
	}

	/**
	 * 返回成功或者失败
	 */
	protected <T> BackResult<T> handleResult(T result) {
		boolean flag = isFlag(result);

		if (flag) {
			return new BackResult(BackResult.SUCCESS_CODE, "操作成功", result);
		} else {
			return new BackResult(BackResult.ERROR_CODE, "操作失败", result);
		}
	}

	/**
	 * 返回成功或者失败，定制失败信息
	 */
	protected <E> BackResult<E> handleResult(E result, String errorMsg) {
		boolean flag = isFlag(result);

		if (flag) {
			return new BackResult(BackResult.SUCCESS_CODE, "操作成功", result);
		} else {
			return new BackResult(BackResult.ERROR_CODE, errorMsg, result);
		}
	}

	private boolean isFlag(Object result) {
		boolean flag;
		if (result instanceof Integer) {
			flag = (Integer) result > 0;
		} else if (result instanceof Boolean) {
			flag = (Boolean) result;
		} else {
			flag = ObjectIsEmptyUtils.isNotEmpty(result);
		}
		return flag;
	}
}
  