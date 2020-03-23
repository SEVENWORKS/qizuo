/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

//springboot 启动项
@SpringBootApplication
//config server启动项
@EnableConfigServer
//eureka client启动项
@EnableDiscoveryClient
public class QiZuoConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(QiZuoConfigApplication.class, args);
    }
}
