/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.core.social.support;

import lombok.Data;

/**
 * The class Social user info.
 */
@Data
public class SocialUserInfo {

	private String providerId;

	private String providerUserId;

	private String nickname;

	private String headimg;

}
