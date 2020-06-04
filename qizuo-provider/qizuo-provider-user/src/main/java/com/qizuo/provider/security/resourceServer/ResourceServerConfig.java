/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.resourceServer;

import com.qizuo.provider.security.resourceServer.exception.AuthenAccessDeniedHandler;
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

/** 认证的资源服务器配置 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired private AuthenAccessDeniedHandler authenAccessDeniedHandler;

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
    http.apply(openIdAuthenticationSecurityConfig)
        .and()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(authenAccessDeniedHandler)
        .and()
        .csrf()
        .disable();
  }
}
