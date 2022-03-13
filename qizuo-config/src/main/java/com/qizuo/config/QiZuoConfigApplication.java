/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 1.局部刷新 局部刷新需要手动针对某个服务进行刷新，通常分为三步 a.提交配置文件b.重启config服务c.刷新地方加上@refreshcode还有@valued.请求刷新局部点
 * 请求地址示例：localhost:9300/port/user/refresh 注意两点: a.记得带上token(post请求) b.有权限控制，这个不是自己security控制的，是他们的
 *
 * TODO 2.自动刷新(WebHooks动态刷新，spring-cloud-bus动态刷新)
 * 注意：缓存的springconfig文件都是当前config服务启动时候取的最终值
 * 根据yml中配置不同环境，取不同环境的后缀
 *
 * 加密解密：配置好keystore之后，会自动新增两个POST接口 /encrypt和 /decrypt
 * http://127.0.0.1:9300/port/config/encrypt post请求body选择row http://127.0.0.1:9300/port/config/encrypt/status检测加密状态
 * 生成jks:keytool -genkeypair -alias qizuo0 -keyalg RSA -dname "CN=Web Server,OU=China,O=www.howardliu.cn,L=Beijing,S=Beijing,C=China" -keypass qizuo0 -keystore qizuo.jks -storepass qizuo0
 * (注意.keystore文件不行，必须.jks文件才可以;TODO 鉴权开启无法使用)
 */
// springboot 启动项
@SpringBootApplication
// config server启动项
@EnableConfigServer
// eureka client启动项
@EnableDiscoveryClient
public class QiZuoConfigApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(QiZuoConfigApplication.class, args);
  }
}
