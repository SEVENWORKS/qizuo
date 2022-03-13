/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 有该注解的方法，表明不需要token鉴权，和token拦截器TokenInterceptor相互搭配.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoNeedAccessAuthentication {

}
