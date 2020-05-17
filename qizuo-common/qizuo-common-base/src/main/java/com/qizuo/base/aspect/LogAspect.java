/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.aspect;


import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.model.base.LogDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.UserUtil;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.http.RequestUtil;
import com.qizuo.util.parse.JacksonUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * The class Log aspect.
 */
@Slf4j
//作用是把当前类标识为一个切面供容器读取(同步)
@Aspect
//普通类放到spring容器
@Component
public class LogAspect {

	/**
	 * 在每个线程中都创建了一个 ThreadLocalMap 对象，每个线程可以访问自己内部 ThreadLocalMap 对象内的 value。通过这种方式，避免资源在多线程间共享
	 * 即线程之间不可能出现数据共享的
	 */
	private ThreadLocal<Date> threadLocal = new ThreadLocal<>();

	/**
	 * 定义一个公共的pointcut
	 *
	 * Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
	 * 方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为此表达式命名。
	 * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
	 */
	@Pointcut("@annotation(com.qizuo.base.annotation.LogAnnotation)")
	public void logAnnotation() {
	}

	/**
	 * Do before,.
	 * 标识一个前置增强方法，相当于BeforeAdvice的功能
	 */
	@Before("logAnnotation()")
	public void doBefore() {
		//进入方法前存放开始时间
		this.threadLocal.set(new Date(System.currentTimeMillis()));
	}

	/**
	 * Do after.
	 * 后置增强，相当于AfterReturningAdvice，方法正常退出时执行
	 * @param joinPoint   the join point
	 * @param returnValue the return value
	 */
	@AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")
	public void doAfter(final JoinPoint joinPoint, final Object returnValue) {
		//当返回的value是backresult类型才做处理
		if (returnValue instanceof BackResult) {
			BackResult result = (BackResult) returnValue;
			//不论成功失败都记录日志
			if (!ObjectIsEmptyUtils.isNull(result)) {
				this.handleLog(joinPoint, result);
			}

		}

	}

	//后置增强-请求和响应日志保存
	private void handleLog(final JoinPoint joinPoint, final Object result) {
		//开始时间
		final Date startTime = this.threadLocal.get();
		//结束时间
		final Date endTime = new Date(System.currentTimeMillis());
		//请求信息
		HttpServletRequest request = RequestUtil.getRequest();
		final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		String requestURI = request.getRequestURI();

		try {
			//获取当前且切点方法注解对象
			LogAnnotation relog = giveController(joinPoint);
			if (relog == null) {
				return;
			}

			//当前登录对象
			UserDto loginUser = UserUtil.qUser();
			//获取客户端操作系统
			final String os = userAgent.getOperatingSystem().getName();
			//获取客户端浏览器
			final String browser = userAgent.getBrowser().getName();
			final String ipAddress = RequestUtil.getRemoteAddr(request);

			//日志存储对象
			LogDto operationLogDto = new LogDto();
			operationLogDto.setClassName(joinPoint.getTarget().getClass().getName());
			operationLogDto.setMethodName(joinPoint.getSignature().getName());
			operationLogDto.setExcuteTime(endTime.getTime() - startTime.getTime());
			operationLogDto.setStartTime(startTime);
			operationLogDto.setEndTime(endTime);
			operationLogDto.setIp(ipAddress);
			operationLogDto.setOs(os);
			operationLogDto.setBrowser(browser);
			operationLogDto.setRequestUrl(requestURI);
			operationLogDto.setGroupId(loginUser.getGroupId());
			operationLogDto.setGroupName(loginUser.getGroupName());
			operationLogDto.setCreatedTime(new Date());
			operationLogDto.setCreator(loginUser.getUserName());
			operationLogDto.setCreatorId(loginUser.getBaseId());
			operationLogDto.setLastOperator(loginUser.getUserName());
			operationLogDto.setLastOperatorId(loginUser.getBaseId());
			operationLogDto.setLogType(relog.logType().getType());
			operationLogDto.setLogName(relog.logType().getName());

			//对请求参数和返回结果进行处理
			getControllerMethodDescription(relog, operationLogDto, result, joinPoint);
			//移除时间
			threadLocal.remove();
			//保存
			log.info("一个普通的请求={}", operationLogDto);
		} catch (Exception ex) {
			log.error("获取注解类出现异常={}", ex.getMessage(), ex);
		}
	}

	/**
	 * 是否存在注解, 如果存在就记录日志
	 */
	private static LogAnnotation giveController(JoinPoint joinPoint) {
		//切点方法集
		Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
		//切点当前方法
		String methodName = joinPoint.getSignature().getName();
		//判断是否包含当前方法并且存在日志注解
		if (null != methods && 0 < methods.length) {
			for (Method met : methods) {
				LogAnnotation relog = met.getAnnotation(LogAnnotation.class);
				if (null != relog && methodName.equals(met.getName())) {
					return relog;
				}
			}
		}
		return null;
	}

	//对请求参数和返回结果进行处理(是否保存)
	private void getControllerMethodDescription(LogAnnotation relog, LogDto operationLog, Object result, JoinPoint joinPoint) {
		//日志是否保存请求参数
		if (relog.isSaveRequestData()) {
			setRequestData(operationLog, joinPoint);
		}
		//日志是否保存返回参数
		if (relog.isSaveResponseData()) {
			setResponseData(operationLog, result);
		}
	}

	//保存返回参数
	private void setResponseData(LogDto requestLog, Object result) {
		try {
			requestLog.setResponseData(String.valueOf(result));
		} catch (Exception e) {
			log.error("获取响应数据,出现错误={}", e.getMessage(), e);
		}
	}

	//保存请求参数
	private void setRequestData(LogDto uacLog, JoinPoint joinPoint) {
		try {
			//请求参数转换
			Object[] args = joinPoint.getArgs();
			if (args.length == 0) {
				return;
			}
			Object[] parameter = new Object[args.length];
			int index = 0;
			for (Object object : parameter) {
				if (object instanceof HttpServletRequest) {
					continue;
				}
				parameter[index] = object;
				index++;
			}

			String requestData = JacksonUtil.toJsonWithFormat(parameter);
			if (requestData.length() > GlobalConstant.HttpConfig.LogAspect_MAX_SIZE) {
				requestData = requestData.substring(GlobalConstant.HttpConfig.LogAspect_MAX_SIZE);
			}

			//保存
			uacLog.setRequestData(requestData);
		} catch (Exception e) {
			log.error("获取响应数据,出现错误={}", e.getMessage(), e);
		}
	}

}
