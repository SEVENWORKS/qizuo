/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.aspect;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.Thread.ThreadLocalMap;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * The class Not display sql aspect.
 */
@Aspect
@Component
public class NotDisplaySqlAspect {
	/**
	 * 定义一个公共的pointcut
	 *
	 * Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
	 * 方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为此表达式命名。
	 * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
	 */
	@Pointcut("@annotation(com.qizuo.base.annotation.NotDisplaySql)")
	private void myPointCut() {
	}

	/**
	 * Before.
	 */
	@Before(value = "myPointCut()")
	public void before() {
		//执行方法前存入一个变量，表明不需要打印sql
		ThreadLocalMap.put(GlobalConstant.SqlConfig.NotDisplaySqlAspect_DISPLAY_SQL, Boolean.FALSE);
	}

	/**
	 * After.
	 */
	@After(value = "myPointCut()")
	public void after() {
		//执行方法后移除该变量
		ThreadLocalMap.remove(GlobalConstant.SqlConfig.NotDisplaySqlAspect_DISPLAY_SQL);
	}
}
