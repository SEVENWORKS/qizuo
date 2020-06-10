/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.authorizationServer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/** security基本配置器，对sercurity基本属性一些配置，比如登录页面，登录url，登出url等等,就是没有spring security oath2的时候会用到这个 */
// @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  /**
   * Configure.
   *
   * @param http the http
   * @throws Exception the exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers()
        .frameOptions()
        .disable() // 请求头设置，这个地方是配置iframe权限，springSecurty使用X-Frame-Options防止网页被Frame，这是去掉
        //				.and()//and()返回一个SecurityBuilder。
        // httpBasic是利用HTTP头部进行认证，访问页面时会由浏览器弹框要求密码，这个是走HTTP协议层面的认证
        // formLogin是基于页面，你需要自己实现一个登录页面，也就是示例中的/usercheck.jsp（名字你可以自己定），里面要有一个登录表单，表单的action和用户名
        // 密码字段名都是框架定死的，然后你需要再实现一个servlet来处理这个表单的action，实现登录，实际上走的是session/cookie认证
        //				.formLogin()//Spring Security支持两种认证方式：formLogin()和httpBasic()。
        //				.loginPage("/login.html")//登陆界面页面跳转URL
        //				.loginProcessingUrl("/login")//登陆界面发起登陆请求的URL
        .and()
        .logout()
        .logoutUrl("/logout") // 发起登出请求的URL
        .and()
        .csrf()
        .disable() // 关闭默认打开的crsf protection，因为打开这个会对自制的一些token认证功能有影响
        .authorizeRequests() // 启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护，为后面设置启用
        .antMatchers("/favicon.icon") // 未登陆用户允许的请求 /**/*.css
        .permitAll() // 未登陆用户允许的请求
        .anyRequest()
        .authenticated(); // 其他请求全部需要登陆
  }
}
