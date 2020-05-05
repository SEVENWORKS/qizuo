/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.core.social;

import org.springframework.social.connect.Connection;

import com.qizuo.security.core.social.support.SocialUserInfo;


/**
 * The class Social controller.
 */
public abstract class BaseSocialController {

	/**
	 * 根据Connection信息构建SocialUserInfo
	 *
	 * @param connection the connection
	 *
	 * @return social user info
	 */
	protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
		SocialUserInfo userInfo = new SocialUserInfo();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}

}
