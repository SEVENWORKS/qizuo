/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.resourceServer.securityConfigurerAdapter;

import com.qizuo.security.service.SecurityUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 认证配置器
 *
 * <p>1. 首先过滤器会调用自身的attemptAuthentication方法，从request中取出authentication,
 * authentication是在org.springframework.security.web.context.SecurityContextPersistenceFilter过滤器中通过捕获用户提交的登录表单中的内容生成的一个org.springframework.security.core.Authentication接口实例.
 *
 * <p>2. 拿到authentication对象后，过滤器会调用ProviderManager类的authenticate方法，并传入该对象
 *
 * <p>3.ProviderManager类的authenticate方法再调用自身的doAuthentication方法，在doAuthentication方法中会调用类中的List<AuthenticationProvider>
 * providers集合中的各个AuthenticationProvider接口实现类中的authenticate(Authentication
 * authentication)方法进行验证，由此可见，真正的验证逻辑是由各个各个AuthenticationProvider接口实现类来完成的,DaoAuthenticationProvider类是默认情况下注入的一个AuthenticationProvider接口实现类
 *
 * <p>4.AuthenticationProvider接口通过UserDetailsService来获取用户信息
 */
@Component
public class OpenIdAuthenticationSecurityConfig
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  // 成功处理
  private final AuthenticationSuccessHandler authenticationSuccessHandler;
  // 异常处理
  private final AuthenticationFailureHandler authenticationFailureHandler;
  private final SecurityUserDetailsSevice userDetailsService;

  // 统一给上依赖
  @Autowired
  public OpenIdAuthenticationSecurityConfig(
      AuthenticationSuccessHandler authenticationSuccessHandler,
      AuthenticationFailureHandler authenticationFailureHandler,
      SecurityUserDetailsSevice userDetailsService) {
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.authenticationFailureHandler = authenticationFailureHandler;
    this.userDetailsService = userDetailsService;
  }

  /**
   * Configure.
   *
   * @param http the http
   */
  @Override
  public void configure(HttpSecurity http) {
    // 自定filter，主要是成功失败的处理器
    OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();
    openIdAuthenticationFilter.setAuthenticationManager(
        http.getSharedObject(AuthenticationManager.class));
    openIdAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    openIdAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
    // 自定义provider，主要是userservice认证
    OpenIdAuthenticationProvider openIdAuthenticationProvider = new OpenIdAuthenticationProvider();
    openIdAuthenticationProvider.setUserDetailsService(userDetailsService);
    // 塞入configure配置中
    http.authenticationProvider(openIdAuthenticationProvider)
        .addFilterAfter(openIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
