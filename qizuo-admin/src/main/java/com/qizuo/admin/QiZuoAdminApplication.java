/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

//springboot启动
@SpringBootApplication
//eureka client启动
@EnableDiscoveryClient
//admin server启动
@EnableAdminServer
//turbine启动(断路器多点监控)
@EnableTurbine
//断路器可视化工具启动
@EnableHystrixDashboard
//断路器启动
@EnableCircuitBreaker
//如果要按照war包方式部署，一定要继承SpringBootServletInitializer提示从这个地方入口(不行的话同时pom中去掉tomcat依赖)
public class QiZuoAdminApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(QiZuoAdminApplication.class, args);
	}
}
