/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.http;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * url转码、解码
 */
@Slf4j
public class UrlUtil {

	/**
	 * URL 解码
	 */
	public static String getURLDecoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLDecoder.decode(str, GlobalConstant.Encode.ENCODE);
		} catch (UnsupportedEncodingException e) {
			log.error("URL解码失败 ex={}", e.getMessage(), e);
		}
		return result;
	}

	/**
	 * URL 转码
	 */
	public static String getURLEncoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLEncoder.encode(str, GlobalConstant.Encode.ENCODE);
		} catch (UnsupportedEncodingException e) {
			log.error("URL转码失败 ex={}", e.getMessage(), e);
		}
		return result;
	}
}