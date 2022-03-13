/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//springboot启动
@SpringBootApplication
//eureka client启动
@EnableDiscoveryClient
//admin server启动
@EnableAdminServer
//下方集成Hystrix断路监控(turbine监控，单独的一块;http://localhost:9400/admin/turbine.stream;http://localhost:9400/admin/hystrix)
//turbine启动(断路器多点监控)
//@EnableTurbine
////断路器可视化工具启动
//@EnableHystrixDashboard
////断路器启动
//@EnableCircuitBreaker
//如果要按照war包方式部署，一定要继承SpringBootServletInitializer提示从这个地方入口(不行的话同时pom中去掉tomcat依赖)
//TODO 监控系统普罗米修斯重新构造
public class QiZuoAdminApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(QiZuoAdminApplication.class, args);
	}
}
