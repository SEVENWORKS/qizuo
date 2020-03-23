/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//注册中心server启动
@EnableEurekaServer
//springboot启动
@SpringBootApplication
public class QiZuoEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(QiZuoEurekaApplication.class, args);
	}
}
