/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config.init.configBeanInit;

import com.qizuo.config.properties.QizuoConfigPropertiesGY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * 定时初始化.
 */
@Configuration
//支持异步，多线程
@EnableAsync
//开启定时
@EnableScheduling
public class TaskInit implements AsyncConfigurer {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private QizuoConfigPropertiesGY qizuoConfigProperties;

	//@Bean标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象
	@Override
	@Bean(name = "taskExecutor")
	public Executor getAsyncExecutor() {
		log.debug("Creating Async Task Executor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(qizuoConfigProperties.getTask().getCorePoolSize());
		executor.setMaxPoolSize(qizuoConfigProperties.getTask().getMaxPoolSize());
		executor.setQueueCapacity(qizuoConfigProperties.getTask().getQueueCapacity());
		executor.setKeepAliveSeconds(qizuoConfigProperties.getTask().getKeepAliveSeconds());
		executor.setThreadNamePrefix(qizuoConfigProperties.getTask().getThreadNamePrefix());
		return new TaskInitExceptionHandlingAsyncTaskExecutor(executor);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
