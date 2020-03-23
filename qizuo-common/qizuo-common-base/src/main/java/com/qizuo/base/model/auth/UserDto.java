/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * user
 */
//get&set
@Data
//swagger
@ApiModel(value = "登录人信息")
public class UserDto implements Serializable {
	//序列化标志
	private static final long serialVersionUID = -1137852221455042256L;

	@ApiModelProperty(value = "用户ID")
	private String userId;
	@ApiModelProperty(value = "登录名")
	private String loginName;
	@ApiModelProperty(value = "用户名")
	private String userName;
	@ApiModelProperty(value = "组织ID")
	private String groupId;
	@ApiModelProperty(value = "组织名称")
	private String groupName;

	public UserDto() {
	}

	public UserDto(String userId, String loginName, String userName) {
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
	}

	public UserDto(String userId, String loginName, String userName, String groupId, String groupName) {
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
		this.groupId = groupId;
		this.groupName = groupName;
	}
}
