/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.baseConfig;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/** 普通资源服务器配置核心 就是没有spring security oath2的时候会用到这个 */
// @Configuration
// @EnableResourceServer
public class BaseResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.headers()
        .frameOptions()
        .disable()
        .and()
        .csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(
            (request, response, authException) ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests()
        .antMatchers("/favicon.icon")
        .permitAll()
        .anyRequest()
        .authenticated();
  }
}
