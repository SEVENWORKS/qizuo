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
 * springmvcjackson解析器，对请求和相应数据进行解析. 这地方主要配置转json的视图解析器，该解析器只有在@requestbody的时候生效
 * 要注意：正常情况下的请求只有formdata类型的会被解析到controller参数上，application/json的请求必须要靠@requestbody来解析。
 * 这个视图解析器有个不好的地方就是解析的json必须为对象，即如果用了@requestbody的地方必须用对象，用string等单独的不行
 */
public class SpringMVCJacksonConverter {
  private SpringMVCJacksonConverter() {}

  public static void buidMvcMessageConverter(List<HttpMessageConverter<?>> converters) {
    // 创建一个jackson解析器
    MappingJackson2HttpMessageConverter jackson2HttpMessageConverter =
        new MappingJackson2HttpMessageConverter();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    ObjectMapper objectMapper =
        new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule(simpleModule);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jackson2HttpMessageConverter.setObjectMapper(objectMapper);
    // 添加进去
    converters.add(jackson2HttpMessageConverter);
  }
}
