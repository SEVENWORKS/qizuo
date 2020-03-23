/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.config;

import com.qizuo.base.interceptor.TokenInterceptor;
import com.qizuo.config.properties.otherProperties.SwaggerProperties;
import com.qizuo.security.core.properties.SecurityConstants;
import com.qizuo.util.spring.SpringMVCJacksonConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring内部的一种配置方式采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制.
 */
@Configuration
//@EnableWebMvc是使用Java 注解快捷配置Spring Webmvc的一个注解。在使用该注解后配置一个继承于WebMvcConfigurerAdapter的配置类即可配置好Spring Webmvc
@EnableWebMvc
@Import(SwaggerProperties.class)
public class UacWebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private TokenInterceptor vueViewInterceptor;

	//静态资源处理
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
	}

	//添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(vueViewInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html", SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
	}

	//视图内容解析器，准换成json
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		SpringMVCJacksonConverter.buidMvcMessageConverter(converters);
	}

}
