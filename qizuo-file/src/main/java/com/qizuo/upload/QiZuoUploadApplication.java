/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//springboot启动
@SpringBootApplication
//eureka client启动
@EnableDiscoveryClient
public class QiZuoUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(QiZuoUploadApplication.class, args);
	}
}
