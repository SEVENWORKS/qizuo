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
   定时任务
    @Component
    @EnableScheduling开启后，可以用下面方式进行定时
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


     下方线程池使用方式1，线程方式调用异步任务(run和start不同，run是不会异步线程的)
	 public class MyRunnable implements Runnable{
		 @Override
		 public void run() {
		 }
	 }
	 new Thread(new MyRunnable()).start();
	 public class MyRunnable2 extends Thread{
	    @Override
	    public void run(){
	     }
	 }
	 new MyRunnable2().start();

     @Autowired
	 Executor taskExecutor;
	 @ResponseBody
	 @RequestMapping("hehe")
	 public String base() {
	 Runnable ra=new MyRunnable();
	 //ra.run();
	 //线程池使用方式
	 taskExecutor.execute(ra);
	 return "333";
	 }

     下方线程池使用方式2
	 @EnableAsync开启后  搭配@Async可以直接异步调用线程任务
	 @Component
	 public class TreadTasks {
	 @Async(taskExecutor)
		 public void startMyTreadTask() {
			System.out.println("this is my async task");
		 }
	 }
 */
@Configuration
//支持异步多线程(多线程)，即可以用上面方式开启
@EnableAsync
//开启定时，开启定时后，可以用上面方式使用线程
@EnableScheduling
public class ThreadPoolInit implements AsyncConfigurer {
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
		//用自定义的异步线程池，或者不用也行
		return new ThreadPoolExceptionHandlingAsyncTaskExecutor(executor);
	}

	//在使用void返回类型的异步方法执行期间抛出异常时要使用的实例
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
