/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.interceptor;

import com.qizuo.util.Thread.ThreadLocalMap;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import java.text.DateFormat;
import java.util.*;

/**
 * mybatis sql 拦截器(拦截update和query)
 * 1.配合注解NotDisplaySql ，可以禁止指定的方法的SQL 不打印控制台。
 * 2.SQL 执行时间超过 关注时间 noticeTime ,以error级别打印到控制台
 * 使用时需要把mybatis logger级别设置为 INFO级别
 */
//表明当前对象是一个Interceptor
@Intercepts({
		//表明要拦截的接口、方法以及对应的参数类型
		@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Slf4j
//spring bean执行顺序定制，注意不是加载顺序
@Order(1)
public class SqlLogInterceptor implements Interceptor {
	//是否开启sql拦截，默认false不开启
	/**
	 * 1.@Value(“#{}”) 表示SpEl表达式通常用来获取bean的属性，或者调用bean的某个方法。当然还有可以表示常量
	 * 2.@Value(“${xxxx}”)注解从配置文件读取值的用法
	 */
	@Value("${SqlLogInterceptor_enableSqlLogInterceptor}")
	private boolean enableSqlLogInterceptor;

	/**
	 * 让mybatis判断，是否要进行拦截，然后做出决定是否生成一个代理t.
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 如果我们的拦截器需要一些变量对象，而且这个对象是支持可配置的，就可以通过这个方法进行配置一些变量
	 */
	@Override
	public void setProperties(Properties properties) {

	}

	/**
	 * mybatis只能拦截四种类型的对象。而intercept方法便是处理拦截到的对象,就是核心方法.
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//start毫秒数
		long start = System.currentTimeMillis();
		//这段就是拦截sql执行获取执行结果
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		List<String> paramList = getParamList(configuration, boundSql);
		Object proceed = invocation.proceed();

		//只有当支持拦截的时候才会去做事情
		if (enableSqlLogInterceptor) {
			//判断执行结果，如果是列表就返回数据量，如果是更新就返回更新结果
			int result = 0;
			if (proceed instanceof ArrayList) {
				ArrayList resultList = (ArrayList) proceed;
				result = resultList.size();
			}
			if (proceed instanceof Integer) {
				result = (Integer) proceed;
			}
			//结束时间
			long end = System.currentTimeMillis();
			long time = end - start;
			//获取是否该方法是否不打印sql注解(该注解是在方法上的)
			Boolean flag = (Boolean) ThreadLocalMap.get(GlobalConstant.SqlConfig.NotDisplaySqlAspect_DISPLAY_SQL);
			//当执行时间超过时间的会报错，这个错误表明执行时间太长了
			if (time >= GlobalConstant.SqlConfig.SqlLogInterceptor_NoticeTime * GlobalConstant.Number.THOUSAND_INT) {
				log.error("执行超过{}秒,sql={}", GlobalConstant.SqlConfig.SqlLogInterceptor_NoticeTime, sql);
				log.error("result={}, time={}ms, params={}", result, time, paramList);
				return proceed;
			}
			//只有DISPLAY_SQL为true的时候，或者简单的说方法上存在这个注解的时候，是不会sql到日志的
			if (flag == null || Objects.equals(flag, true)) {
				log.info("sql={}", sql);
				log.info("result={},time={}ms, params={}", result, time, paramList);
			}
		}
		return proceed;
	}

	/**
	 * 获取sql执行参数集合。
	 * @return the param list
	 */
	private List<String> getParamList(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		List<String> params = new ArrayList<>();
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				params.add(getParameterValue(parameterObject));
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						params.add(getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						params.add(getParameterValue(obj));
					}
				}
			}
		}
		return params;
	}
	/**
	 * 获取sql执行参数集合2。
	 */
	private String getParameterValue(Object obj) {
		String value;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(obj) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}
}
