/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 用户验证系统.
 */
//启动熔断降级服务
@EnableHystrix
//启用feign负载均衡调用客户端
@EnableFeignClients
//启用eurake服务发现
@EnableDiscoveryClient
//启动springboot入口
@SpringBootApplication
//启用事务
//Spring Boot 使用事务非常简单，首先使用注解 @EnableTransactionManagement 开启事务支持后，然后在访问数据库的Service方法上添加注解 @Transactional 便可。
@EnableTransactionManagement
public class QiZuoProviderUserApplication {

	/**
	 * 启动方法.
	 */
	public static void main(String[] args) {
		SpringApplication.run(QiZuoProviderUserApplication.class, args);
	}
}
