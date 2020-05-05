/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.core.social.weixin.api;

/**
 * 微信API调用接口
 */
public interface Weixin {

	/**
	 * Gets user info.
	 *
	 * @param openId the open id
	 *
	 * @return the user info
	 */
	WeixinUserInfo getUserInfo(String openId);

}
