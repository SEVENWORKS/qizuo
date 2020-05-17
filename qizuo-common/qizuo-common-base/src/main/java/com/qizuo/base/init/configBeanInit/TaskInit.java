/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.init.configBeanInit;

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
 * 任务线程池初始化.
 * 通过实现AsyncConfigurer自定义线程池，包含异常处理
 * 实现AsyncConfigurer接口对异常线程池更加细粒度的控制
 */

/**
 * @Component
	public class Jobs {
	//表示方法执行完成后5秒
	 @Scheduled(fixedDelay = 5000)
	 public void fixedDelayJob() throws InterruptedException {
	 System.out.println("fixedDelay 每隔5秒" + new Date());
	 }

	 //表示每隔3秒
	 @Scheduled(fixedRate = 3000)
	 public void fixedRateJob() {

	 System.out.println("fixedRate 每隔3秒" + new Date());
	 }

	 //表示每天8时30分0秒执行
	 @Scheduled(cron = "0 0,30 0,8 ? * ? ")
	 public void cronJob() {
	 System.out.println(new Date() + " ...>>cron....");
	 }
	 }
 */

/**
 * 线程池使用，线程正常用就行，线程池已经初始化了
 * @Autowired
	Executor taskExecutor;

	 @ResponseBody
	 @RequestMapping("hehe")
	 public String base() {
	 Runnable ra=new MyRunnable();
	 ra.run();
	 //线程池使用方式
	 taskExecutor.execute(ra);
	 return "333";
	 }
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
		//spring包下的，是sring为我们提供的线程池类，区别于JDK自带的ThreadPoolExecutor
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(qizuoConfigProperties.getTask().getCorePoolSize());
		executor.setMaxPoolSize(qizuoConfigProperties.getTask().getMaxPoolSize());
		executor.setQueueCapacity(qizuoConfigProperties.getTask().getQueueCapacity());
		executor.setKeepAliveSeconds(qizuoConfigProperties.getTask().getKeepAliveSeconds());
		executor.setThreadNamePrefix(qizuoConfigProperties.getTask().getThreadNamePrefix());
		return new TaskInitExceptionHandlingAsyncTaskExecutor(executor);
	}

	//在使用void返回类型的异步方法执行期间抛出异常时要使用的实例
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
