/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.resourceServer;

import com.qizuo.provider.security.resourceServer.exception.AuthenAccessDeniedHandler;
import com.qizuo.provider.security.resourceServer.exception.AuthenticationEntryPointHandler;
import com.qizuo.provider.security.resourceServer.securityConfigurerAdapter.OpenIdAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/** 认证的资源服务器配置,如果存在springsecurity的资源服务器的包，就必须要配置这个，否则报错 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired private AuthenAccessDeniedHandler authenAccessDeniedHandler;
  @Autowired private AuthenticationEntryPointHandler authenticationEntryPointHandler;

  @Autowired private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

  @Resource private DataSource dataSource;

  /**
   * 记住我功能的token存取器配置
   *
   * @return the persistent token repository
   */
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    //		tokenRepository.setCreateTableOnStartup(true); // 第一次启动创建
    return tokenRepository;
  }

  /**
   * Configure.添加了一些认证配置和异常处理
   *
   * @param http the http
   * @throws Exception the exception
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.apply(openIdAuthenticationSecurityConfig) // 添加自定义配置，这个可以apply多个
        .and()
        .headers()
        .frameOptions()
        .disable() // 请求头设置，这个地方是配置iframe权限，springSecurty使用X-Frame-Options防止网页被Frame，这是去掉
        .and()
        .exceptionHandling()
        .accessDeniedHandler(authenAccessDeniedHandler) // 异常处理的handler
        .authenticationEntryPoint(authenticationEntryPointHandler) // 异常处理的handler
        .and()
        .authorizeRequests() // 启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护，为后面设置启用
        .antMatchers("/favicon.icon") // 未登陆用户允许的请求 /**/*.css
        .permitAll() // 未登陆用户允许的请求
        .anyRequest()
        .authenticated() // 其他请求全部需要登陆
        .and()
        .csrf()
        .disable(); // 关闭csrf跨域请求设置
  }
}
