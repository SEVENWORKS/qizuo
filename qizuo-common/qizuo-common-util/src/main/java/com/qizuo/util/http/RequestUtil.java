/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.http;

import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.exception.BusinessException;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.util.Thread.ThreadLocalMap;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 请求request解析工具.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

	/**
	 * Gets request.
	 *
	 * @return the request
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获得用户远程地址
	 *
	 * @param request the request
	 *
	 * @return the string
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader(GlobalConstant.HttpConfig.X_REAL_IP);
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HttpConfig.X_FORWARDED_FOR);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HttpConfig.PROXY_CLIENT_IP);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HttpConfig.WL_PROXY_CLIENT_IP);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HttpConfig.HTTP_CLIENT_IP);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HttpConfig.HTTP_X_FORWARDED_FOR);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.HttpConfig.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (GlobalConstant.HttpConfig.LOCALHOST_IP.equals(ipAddress) || GlobalConstant.HttpConfig.LOCALHOST_IP_16.equals(ipAddress)) {
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					log.error("获取IP地址, 出现异常={}", e.getMessage(), e);
				}
				assert inet != null;
				ipAddress = inet.getHostAddress();
			}
			log.info("获取IP地址 ipAddress={}", ipAddress);
		}
		// 对于通过多个代理的情况, 第一个IP为客户端真实IP,多个IP按照','分割 //"***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > GlobalConstant.HttpConfig.MAX_IP_LENGTH) {
			if (ipAddress.indexOf(GlobalConstant.Symbol.COMMA) > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(GlobalConstant.Symbol.COMMA));
			}
		}
		return ipAddress;
	}

	/**
	 * Gets login user.
	 *
	 * @return the login user
	 */
	public static UserDto getLoginUser() {
		UserDto loginAuthDto = (UserDto) ThreadLocalMap.get(GlobalConstant.Role.SUPER);
		if (ObjectIsEmptyUtils.isEmpty(loginAuthDto)) {
			throw new BusinessException(ResultCodeEnum.UAC10011039);
		}
		return loginAuthDto;

	}

	/**
	 * Gets auth header.
	 *
	 * @param request the request
	 *
	 * @return the auth header
	 */
	public static String getAuthHeader(HttpServletRequest request) {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (org.apache.commons.lang.StringUtils.isEmpty(authHeader)) {
			throw new BusinessException(ResultCodeEnum.UAC10011040);
		}
		return authHeader;
	}

	public static String[] extractAndDecodeHeader(String header) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(GlobalConstant.Symbol.MH);

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[]{token.substring(0, delim), token.substring(delim + 1)};
	}
}
