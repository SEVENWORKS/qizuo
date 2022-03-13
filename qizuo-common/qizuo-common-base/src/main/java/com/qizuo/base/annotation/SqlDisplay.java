/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.annotation;

import java.lang.annotation.*;

/**
 * 配合 SqlLogInterceptor对指定方法禁止打印SQL到日志，否则默认是在拦截器中打印的
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
//如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
@Inherited
@Documented
public @interface SqlDisplay {
}
