/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户token信息存储
 */
//通过设置callSuper=true，可以在loombok生成的equals和hashCode方法里包含超类的方法
@EqualsAndHashCode(callSuper = true)
@Data
public class TokenDto extends UserDto {
	private static final long serialVersionUID = 3136723742371575367L;

	/**
	 * tokenid
	 */
	private String id;

	/**
	 * 版本号
	 */
	private Integer version;
	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 创建人ID
	 */
	private String creatorId;

	/**
	 * 创建时间
	 */
	//时间入参和出参格式化
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;

	/**
	 * 最近操作人
	 */
	private String lastOperator;

	/**
	 * 最后操作人ID
	 */
	private String lastOperatorId;

	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 父ID
	 */
	private String pid;

	/**
	 * 访问token
	 */
	private String accessToken;

	/**
	 * 刷新token
	 */
	private String refreshToken;

	/**
	 * 访问token的生效时间(秒)
	 */
	private Integer accessTokenValidity;

	/**
	 * 刷新token的生效时间(秒)
	 */
	private Integer refreshTokenValidity;

	/**
	 * 0 在线 10已刷新 20 离线
	 */
	private Integer status;

	/**
	 * 登陆人Ip地址
	 */
	private String loginIp;

	/**
	 * 登录地址
	 */
	private String loginLocation;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 浏览器类型
	 */
	private String browser;
}
