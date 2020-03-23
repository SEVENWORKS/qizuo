/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.admin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置
 */
//加载spring的容器类，作用于类上
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 配置安全性相关信息.
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable()
				.and()
				.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/login")
				.and()
				.logout().logoutUrl("/logout")
				.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/**", "/applications/**", "/api/applications/**", "/login.html", "/**/*.css", "/img/**", "/third-party/**")
				.permitAll()
				.anyRequest().authenticated();
	}
}