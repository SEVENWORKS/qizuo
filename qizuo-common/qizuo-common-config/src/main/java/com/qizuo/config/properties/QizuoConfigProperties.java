/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config.properties;


import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.otherProperties.TaskProperties;
import com.qizuo.config.properties.otherProperties.SwaggerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置启动核心2.
 * 这个类的作用就是把所有配置项集合到QizuoConfigProperties这个对象中
 */
//get和set方法
@Data
//把配置文件的信息读取并自动封装成实体类，prefix代表配置文件中前缀
@ConfigurationProperties(prefix = GlobalConstant.Url$Path.ROOT_PREFIX)
public class QizuoConfigProperties {
	private TaskProperties task = new TaskProperties();
	private SwaggerProperties swagger = new SwaggerProperties();
}
