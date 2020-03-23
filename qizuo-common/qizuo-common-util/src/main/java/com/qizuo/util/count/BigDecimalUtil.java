/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.util.count;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 加减乘除计算.
 */
//@NoArgsConstructor: 自动生成无参数构造函数。
//@AllArgsConstructor: 自动生成全参数构造函数。
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BigDecimalUtil {

	/**
	 * 加.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 *
	 * @return the big decimal
	 */
	public static BigDecimal add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2);
	}

	/**
	 * 减.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 *
	 * @return the big decimal
	 */
	public static BigDecimal sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2);
	}


	/**
	 * 乘.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 *
	 * @return the big decimal
	 */
	public static BigDecimal mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2);
	}

	/**
	 * 除.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 *
	 * @return the big decimal
	 */
	public static BigDecimal div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
	}

}
