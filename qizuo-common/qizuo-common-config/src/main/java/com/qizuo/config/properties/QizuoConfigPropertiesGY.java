/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config.properties;


import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.otherProperties.TaskPropertiesGY;
import com.qizuo.config.properties.otherProperties.SwaggerPropertiesGY;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * yaml配置启动核心2.
 * 这个类的作用就是把所有配置项集合到QizuoConfigProperties这个对象中
 * 这个类主要是配合yaml和xml的配置项进行处理，它下面的类都是私有变量，即带Y标识的properties
 */
//get和set方法
@Data
//把配置文件的信息读取并自动封装成实体类，prefix代表配置文件中前缀
@ConfigurationProperties(prefix = GlobalConstant.Url$Path.ROOT_PREFIX)
public class QizuoConfigPropertiesGY {
	private TaskPropertiesGY task = new TaskPropertiesGY();
	private SwaggerPropertiesGY swagger = new SwaggerPropertiesGY();
}
