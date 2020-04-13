/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * yaml配置启动核心 扫描全局的ConfigurationProperties注解，获取配置项.
 */
//@Configuration标注在类上，相当于把该类作为spring的xml配置文件中的<beans>
@Configuration
//使使用 @ConfigurationProperties 注解的类生效。
/**
 * 如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，
 * 那么在IOC容器中是获取不到properties 配置文件转化的bean。
 * 说白了 @EnableConfigurationProperties 相当于把使用 @ConfigurationProperties 的类进行了一次注入。
 */
@EnableConfigurationProperties(QizuoConfigPropertiesGY.class)
public class QizuoConfigGY {
}
