/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.core.social.qq.config;

import com.qizuo.config.properties.pluginProperties.securityProperties.QQProperties;
import com.qizuo.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.qizuo.config.properties.pluginProperties.securityProperties.SecurityPropertiesY;


/**
 * The class Qq auto config.
 */
@Configuration
@ConditionalOnProperty(prefix = "paascloud.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	private final SecurityPropertiesY securityProperties;

	@Autowired
	public QQAutoConfig(SecurityPropertiesY securityProperties) {
		this.securityProperties = securityProperties;
	}

	/**
	 * Create connection factory connection factory.
	 *
	 * @return the connection factory
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
