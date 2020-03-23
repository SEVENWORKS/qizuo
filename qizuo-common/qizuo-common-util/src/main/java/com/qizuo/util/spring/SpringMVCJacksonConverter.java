/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;


/**
 * springmvcjackson解析器，对请求和相应数据进行解析.
 */
public class SpringMVCJacksonConverter {
	private SpringMVCJacksonConverter() {
	}

	public static void buidMvcMessageConverter(List<HttpMessageConverter<?>> converters) {
		//创建一个jackson解析器
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module())
				.registerModule(new JavaTimeModule())
				.registerModule(simpleModule);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jackson2HttpMessageConverter.setObjectMapper(objectMapper);
		//添加进去
		converters.add(jackson2HttpMessageConverter);
	}

}
