/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.aspect;

import com.qizuo.base.annotation.ValidateRequestAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 请求参数验证aop.
 */
@Component
@Aspect
@Slf4j
public class ValidateRequestAop {
	/**
	 * 定义一个公共的pointcut
	 *
	 * Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
	 * 方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为此表达式命名。
	 * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
	 */
	@Pointcut("@annotation(com.qizuo.base.annotation.ValidateRequestAnnotation)")
	public void validateAnnotation() {
	}

	/**
	 * Do before.
	 */
	@Before("validateAnnotation()")
	public void doBefore() {
	}

	/**
	 * Do after.
	 */
	@AfterReturning(pointcut = "validateAnnotation()")
	public void doAfter(final JoinPoint joinPoint) {
		//切点方法名
		String methodName = joinPoint.getSignature().getName();
		//target
		Object target = joinPoint.getTarget();
		//根据类和方法名获取方法内部方法/获取参数
		Method method = getMethodByClassAndName(target.getClass(), methodName);
		Object[] objects = joinPoint.getArgs();
		//断言方法不是null
		assert method != null;
		//判断方法上的注解是否是验证注解
		ValidateRequestAnnotation annotation = (ValidateRequestAnnotation) getAnnotationByMethod(method, ValidateRequestAnnotation.class);
		//不为null的时候，就表明是验证注解
		if (annotation != null) {
			//使用BindingResult验证参数，这个需要配合具体实体类
			BindingResult bindingResult = null;
			for (Object arg : objects) {
				//转换成bindingResult
				if (arg instanceof BindingResult) {
					bindingResult = (BindingResult) arg;
				}
				//验证bindingResult
				if (bindingResult != null && bindingResult.hasErrors()) {
					//获取字段报错信息
					String errorInfo = bindingResult.getFieldError().getDefaultMessage();
					//抛出异常
					throw new IllegalArgumentException(errorInfo);
				}
			}
		}
	}

	/**
	 * 判断该方法上的注解是否包含指定的注解，这里指的是验证注解
	 */
	private Annotation getAnnotationByMethod(Method method, Class annoClass) {
		Annotation[] all = method.getAnnotations();
		for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}

	/**
	 * 根据类和方法名得到方法
	 */
	private Method getMethodByClassAndName(Class c, String methodName) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
}
