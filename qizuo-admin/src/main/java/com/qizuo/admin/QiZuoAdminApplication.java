/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

//springboot启动
@SpringBootApplication
//eureka client启动
@EnableDiscoveryClient
//admin server启动
//@EnableAdminServer
//turbine启动(断路器多点监控)
@EnableTurbine
//断路器可视化工具启动
@EnableHystrixDashboard
//断路器启动
@EnableCircuitBreaker
public class QiZuoAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(QiZuoAdminApplication.class, args);
	}
}
