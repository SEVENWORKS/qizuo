/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.annotation;

import java.lang.annotation.*;

/**
 * 请求参数验证注解，有这个注解表明参数需要验证，配合切面进行搭配(BindingResult进行搭配使用).
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateRequestAnnotation {
	/**
	 * Is validate boolean.
	 */
	boolean isValidate() default true;
}