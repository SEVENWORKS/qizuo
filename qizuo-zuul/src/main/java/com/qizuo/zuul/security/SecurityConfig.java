/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/** 安全配置 */
// 加载spring的容器类，作用于类上
@Configuration
// 该注解表示自己是oauth2客户端，也即单点登录客户端(即从这端进行登录)
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Configure.
   *
   * @param http the http
   * @throws Exception the exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
  }
}
