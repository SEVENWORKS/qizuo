/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// springboot启动
@SpringBootApplication
// eureka client启动
@EnableDiscoveryClient
// 启用事务
// Spring Boot 使用事务非常简单，首先使用注解 @EnableTransactionManagement 开启事务支持后，然后在访问数据库的Service方法上添加注解
// @Transactional 便可。
@EnableTransactionManagement
public class QiZuoProviderCommonApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(QiZuoProviderCommonApplication.class, args);
  }
}
