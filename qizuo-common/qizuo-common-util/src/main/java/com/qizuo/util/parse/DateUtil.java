/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.parse;


import com.xiaoleilu.hutool.date.DateField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 时间工具.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

	/**
	 * 获取系统前时间.
	 */
	public static String getBeforeTime(int minute) {
		Date newDate = com.xiaoleilu.hutool.date.DateUtil.offset(new Date(), DateField.MINUTE, -minute);
		return com.xiaoleilu.hutool.date.DateUtil.formatDateTime(newDate);
	}
}
