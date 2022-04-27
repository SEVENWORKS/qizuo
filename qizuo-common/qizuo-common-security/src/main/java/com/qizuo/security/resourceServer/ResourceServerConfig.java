/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.resourceServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 认证的资源服务器配置,如果存在springsecurity的资源服务器的包，就必须要配置这个，否则报错 !注意这地方是资源服务器
 * 认证服务器主要用于获取token，即oauth/token等一些url的访问 资源服务器主要用于资源获取，比如controller中方法
 * 所以在配置认证服务的服务器，并不代表我可以去访问它的资源，如果要访问它的资源，则还需要配置资源服务器
 * 注意！暂时只用这一个配置
 */
@Configuration
@EnableResourceServer
@RefreshScope
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启验证，具体的可以看AuthorizationServerConfig这个上面注释
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  // 是否swagger
  @Value("${swagger}")
  private Boolean swagger;
  // 当前服务器ID
  @Value("${qizuo.security.oauth2.resourceId}")
  private String resourceId;

//  @Autowired private AuthenAccessDeniedHandler authenAccessDeniedHandler;
//  @Autowired private AuthenticationEntryPointHandler authenticationEntryPointHandler;
//  @Autowired private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
//  @Resource private DataSource dataSource;

  /**
   * 记住我功能的token存取器配置
   *
   * @return the persistent token repository
   */
//  @Bean
//  public PersistentTokenRepository persistentTokenRepository() {
//    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//    tokenRepository.setDataSource(dataSource);
//    //		tokenRepository.setCreateTableOnStartup(true); // 第一次启动创建
//    return tokenRepository;
//  }

  /**
   * Configure.添加了一些认证配置和异常处理
   *
   * @param http the http
   * @throws Exception the exception
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http // 添加自定义配置，这个可以apply多个
        .headers()
        .frameOptions()
        .disable() // 请求头设置，这个地方是配置iframe权限，springSecurty使用X-Frame-Options防止网页被Frame，这是去掉
        .and()
        .exceptionHandling()
//        .accessDeniedHandler(authenAccessDeniedHandler) // 异常处理的handler
//        .authenticationEntryPoint(authenticationEntryPointHandler) // 异常处理的handler
        .and()
        .authorizeRequests() // 启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护，为后面设置启用
        .antMatchers(
            swagger ? "/webjars/springfox-swagger-ui/**" : "/favicon.icon",
            swagger ? "/swagger-resources/**" : "/favicon.icon",
            swagger ? "/swagger-ui.html" : "/favicon.icon",
            swagger ? "/v2/api-docs" : "/favicon.icon",
            "/**/qizuo/**","/druid/**","/error") // 未登陆用户允许的请求 /**/*.css  动态配置swagger
        .permitAll() // 未登陆用户允许的请求
        .anyRequest()
        .authenticated() // 其他请求全部需要登陆
        .and()
        .csrf()
        .disable(); // 关闭csrf跨域请求设置
  }

  /**
   * 这个方法主要是配置oauth2访问服务器权限配置，只有resourceId相同的才可以认证通过
   * @param resourceServerSecurityConfiguration
   */
  @Override
  public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfiguration){
    resourceServerSecurityConfiguration.resourceId(resourceId);
  }
}
