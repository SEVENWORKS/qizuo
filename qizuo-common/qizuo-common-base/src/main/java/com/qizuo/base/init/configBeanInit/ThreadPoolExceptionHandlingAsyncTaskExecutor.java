/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.init.configBeanInit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 自定义任务执行器_异步线程执行器(多线程执行器).
 * AsyncTaskExecutor异步线程池
 * 通过实现 InitializingBean ,DisposableBean 接口实现初始化方法和销毁前操作
 */
@Slf4j
public class ThreadPoolExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor, InitializingBean, DisposableBean {

	private final AsyncTaskExecutor executor;

	/**
	 * Instantiates a new Exception handling async task executor.
	 *
	 * @param executor the executor
	 */
	ThreadPoolExceptionHandlingAsyncTaskExecutor(AsyncTaskExecutor executor) {
		this.executor = executor;
	}

	/**
	 * Execute.
	 * 无返回值用execute来处理任务
	 * @param task the task
	 */
	@Override
	public void execute(Runnable task) {
		executor.execute(createWrappedRunnable(task));
	}

	/**
	 * Execute.
	 *
	 * @param task         the task
	 * @param startTimeout the start timeout
	 */
	@Override
	public void execute(Runnable task, long startTimeout) {
		executor.execute(createWrappedRunnable(task), startTimeout);
	}

	/**
	 * Submit future.
	 * 有返回值用submit来处理任务
	 * @return the future
	 */
	@Override
	public Future<?> submit(Runnable task) {
		return executor.submit(createWrappedRunnable(task));
	}

	/**
	 * Submit future.
	 *
	 * @param <T>  the type parameter
	 * @param task the task
	 *
	 * @return the future
	 */
	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return executor.submit(createCallable(task));
	}

	//有回调的执行
	//Runnable是一种有很大局限性的抽象，虽然run()能写入到日志文件或者将其结果放入某个共享的数据结构，但它不能返回一个值或者抛出一个受检查的异常。
	//Callable是一种更好的抽象：它认为主入口点（即call()）将返回一个值，并可能抛出一个异常。
	private <T> Callable<T> createCallable(final Callable<T> task) {
		return () -> {
			try {
				return task.call();
			} catch (Exception e) {
				exceptionHandle(e);
				throw e;
			}
		};
	}

	//普通执行
	private Runnable createWrappedRunnable(final Runnable task) {
		return () -> {
			try {
				task.run();
			} catch (Exception e) {
				exceptionHandle(e);
			}
		};
	}

	/**
	 * exception 处理.
	 *
	 * @param e the e
	 */
	private void exceptionHandle(Exception e) {
		log.error("Caught async exception", e);
	}

	/**
	 * DisposableBean Destroy.
	 * 销毁前操作
	 */
	@Override
	public void destroy() throws Exception {
		if (executor instanceof DisposableBean) {
			DisposableBean bean = (DisposableBean) executor;
			bean.destroy();
		}
	}

	/**
	 * InitializingBean After properties set 初始化前操作.
	 * 如果bean实现了InitializingBean接口，会自动调用afterPropertiesSet方法
	 *
	 1、Spring为bean提供了两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，或者在配置文件中通过init-method指定，两种方式可以同时使用。

	 2、实现InitializingBean接口是直接调用afterPropertiesSet方法，比通过反射调用init-method指定的方法效率要高一点，但是init-method方式消除了对spring的依赖。

	 3、如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法。
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (executor instanceof InitializingBean) {
			InitializingBean bean = (InitializingBean) executor;
			bean.afterPropertiesSet();
		}
	}
}
