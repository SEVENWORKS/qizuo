/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 1.局部刷新 局部刷新需要手动针对某个服务进行刷新，通常分为三步 a.提交配置文件 b.请求刷新局部点 c.刷新地方加上@refreshcode还有@value
 * 请求地址示例：localhost:9300/port/user/refresh 注意两点: a.记得带上token b.有权限控制，这个不是自己security控制的，是他们的<br>
 * 2.自动刷新
 */
// springboot 启动项
@SpringBootApplication
// config server启动项
@EnableConfigServer
// eureka client启动项
@EnableDiscoveryClient
public class QiZuoConfigApplication {
  public static void main(String[] args) {
    SpringApplication.run(QiZuoConfigApplication.class, args);
  }
}
