/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// springboot启动
@SpringBootApplication
// eureka client启动
@EnableDiscoveryClient
public class QiZuoProviderFileApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(QiZuoProviderFileApplication.class, args);
  }
}
