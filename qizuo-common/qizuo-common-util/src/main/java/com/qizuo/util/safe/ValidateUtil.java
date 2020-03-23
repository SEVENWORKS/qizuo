/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.safe;


import com.qizuo.util.common.ObjectIsEmptyUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {

	/**
	 * 匹配手机号码, 支持+86和86开头
	 */
	private static final String REGX_MOBILENUM = "^((\\+86)|(86))?(13|15|17|18)\\d{9}$";

	/**
	 * 匹配手机号码, 支持+86和86开头
	 */
	private static final String REGX_MOBILENUM2 = "^((\\+?86)|(\\(\\+86\\)))?(13[0-9][0-9]{8}|14[0-9]{9}|15[0-9][0-9]{8}|17[0-9][0-9]{8}|18[0-9][0-9]{8})$";

	/**
	 * 匹配邮箱帐号
	 */
	private static final String REGX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	/**
	 * 匹配手机号码（先支持13, 15, 17, 18开头的手机号码）.
	 *
	 * @param inputStr the input str
	 *
	 * @return the boolean
	 */
	public static Boolean isMobileNumber2(String inputStr) {
		return !ObjectIsEmptyUtils.isNull(inputStr) && inputStr.matches(REGX_MOBILENUM);
	}

	/**
	 * 校验手机号码是否合法.
	 */
	public static boolean isMobileNumber(final String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return false;
		}
		final String reg = REGX_MOBILENUM2;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	/**
	 * 校验是否是邮箱.
	 *
	 * @param str the str
	 *
	 * @return the boolean
	 */
	public static boolean isEmail(String str) {
		boolean bl = true;
		if (ObjectIsEmptyUtils.isSEmptyOrNull(str) || !str.matches(REGX_EMAIL)) {
			bl = false;
		}
		return bl;
	}
}
