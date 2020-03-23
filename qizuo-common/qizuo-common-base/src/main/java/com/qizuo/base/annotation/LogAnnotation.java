/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.annotation;

import com.qizuo.config.properties.baseProperties.LogTypeEnum;

import java.lang.annotation.*;


/**
 * 操作日志,该注解加在方法上，记录整个请求过程，和切面相配合.
 */
//修饰对象范围(方法/方法参数)
@Target({ElementType.PARAMETER, ElementType.METHOD})
//注解存在生命周期
/**
 * 首先要明确生命周期长度 SOURCE < CLASS < RUNTIME ，所以前者能作用的地方后者一定也能作用。
 一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解，比如@Deprecated使用RUNTIME注解
 如果要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解；
 如果只是做一些检查性的操作，比如 @Override 和 @SuppressWarnings，使用SOURCE 注解。
 */
@Retention(RetentionPolicy.RUNTIME)
//文档注解，表明Javadoc的时候这个注解会被记下
@Documented
public @interface LogAnnotation {
	/**
	 * 日志类型
	 *
	 * @return the log type enum
	 */
	LogTypeEnum logType() default LogTypeEnum.OPERATION_LOG;

	/**
	 * 是否保存请求的参数
	 *
	 * @return the boolean
	 */
	boolean isSaveRequestData() default false;

	/**
	 * 是否保存响应的结果
	 *
	 * @return the boolean
	 */
	boolean isSaveResponseData() default false;
}
