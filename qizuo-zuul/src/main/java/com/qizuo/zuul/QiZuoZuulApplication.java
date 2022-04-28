/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// springboot启动
@SpringBootApplication
// 注册中心 client启动
@EnableDiscoveryClient
// zuul server启动
@EnableZuulProxy
// 熔断启动(对@EnableCircuitBreaker注解的封装),开启之后就可以在requestMapping方法上加上如下熔断降级，注意这个和yaml中execution.isolation.thread.timeoutInMilliseconds配置value
//@HystrixCommand(
//  commandProperties ={
//    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="3000" )
//  }
//  ,fallbackMethod ="myFallBack" //回退方法
//)
@EnableHystrix
// swagger和zuul结合开启
@EnableSwaggerButler
public class QiZuoZuulApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(QiZuoZuulApplication.class, args);
  }

  // 允许跨域设置
  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    // 允许跨域(注意和csrf关系，这个主要解决跨域；csrf关闭主要解决跨站伪token的问题)
    config.setAllowCredentials(false);
    // #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
    config.addAllowedOrigin("*");
    // #允许访问的头信息,*表示全部
    config.addAllowedHeader("*");
    // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
    config.setMaxAge(18000L);
    // 允许提交请求的方法，*表示全部允许
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    // 允许Get的请求方法
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
