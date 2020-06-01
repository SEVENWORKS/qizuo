/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security基本配置器，对sercurity基本属性一些配置，比如登录页面，登录url，登出url等等
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Configure.
	 *
	 * @param http the http
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable()//请求头设置，这个地方是配置iframe权限，springSecurty使用X-Frame-Options防止网页被Frame，这是去掉
				.and()//and()返回一个SecurityBuilder。
				.formLogin()//Spring Security支持两种认证方式：formLogin()和httpBasic()。
				.loginPage("/login.html")//登陆界面页面跳转URL
				.loginProcessingUrl("/login")//登陆界面发起登陆请求的URL
				.and()
				.logout().logoutUrl("/logout")//发起登出请求的URL
				.and()
				.csrf().disable()//关闭默认打开的crsf protection，因为打开这个会对自制的一些token认证功能有影响
				.authorizeRequests()//启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护，为后面设置启用
				.antMatchers("/api/**", "/applications/**", "/api/applications/**", "/login.html", "/**/*.css", "/img/**", "/third-party/**")//未登陆用户允许的请求
				.permitAll()//未登陆用户允许的请求
				.anyRequest().authenticated();//其他请求全部需要登陆
	}
}