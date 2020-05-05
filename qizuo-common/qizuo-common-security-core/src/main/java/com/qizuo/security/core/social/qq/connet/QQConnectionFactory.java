/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.qizuo.security.core.social.qq.api.QQ;

/**
 * The class Qq connection factory.
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * Instantiates a new Qq connection factory.
	 *
	 * @param providerId the provider id
	 * @param appId      the app id
	 * @param appSecret  the app secret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
