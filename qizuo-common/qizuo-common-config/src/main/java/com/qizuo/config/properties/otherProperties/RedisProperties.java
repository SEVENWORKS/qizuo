/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.otherProperties;

import lombok.Data;

/**
 * redis properties.
 */
@Data
public class RedisProperties {
	public static final String RESET_PWD_TOKEN_KEY = "qizuo:restPwd";
	public static final String ACTIVE_USER = "qizuo:activeUser";
	public static final String SEND_SMS_COUNT = "qizuo:sms:count";
	public static final String SEND_EMAIL_CODE = "qizuo:email:code";
	public static final String ACCESS_TOKEN = "qizuo:token:accessToken";
	public static final String UPLOAD_FILE_SIZE = "qizuo:file:upload_file_size";
	public static final int REF_NO_MAX_LENGTH = 100;
}
