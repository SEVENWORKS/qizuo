/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.init;

import com.qizuo.base.interceptor.SqlLogInterceptor;
import com.qizuo.base.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 加载基本配置bean 这个配置中文件不管在哪个项目都会加载  其它的初始化文件需要自己引入  通过spi方式进行加载.
 */
@Configuration
public class ConfigBeanInit {
	//开启负载均衡
	@LoadBalanced
	@Bean
	//spring框架提供的RestTemplate类可用于在应用中调用rest服务，它简化了与http服务的通信方式，
	//统一了RESTful的标准，封装了http链接， 我们只需要传入url及返回值类型即可。相较于之前常用的HttpClient，RestTemplate是一种更优雅的调用RESTful服务的方式
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	//sql打印
	@Bean
	public SqlLogInterceptor sqlLogInterceptor() {
		return new SqlLogInterceptor();
	}

	//token管理
	@Bean
	//1、@ConditionalOnBean（xxx.class）就是为了判断 xxx.class是否存在，并已注入了springboot容器里了;
	//2、@ConditionalOnMissingBean 则是在第一点不存在的情况下起作用，即不存在容器中，则会被注入到容器中
	@ConditionalOnMissingBean(HandlerInterceptor.class)
	//prefix为配置文件中的前缀,
	//其中name用来从application.properties中读取某个属性值。
	//如果该值为空，则返回false;
	//如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。
	//如果返回值为false，则该configuration不生效；为true则生效。
	@ConditionalOnProperty(prefix = "qizuo.interceptor", name = "token", havingValue = "true")
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}
}
