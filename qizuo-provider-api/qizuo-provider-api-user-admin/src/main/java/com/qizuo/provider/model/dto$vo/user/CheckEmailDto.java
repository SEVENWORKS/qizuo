/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.model.dto$vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * The class Check user name dto.
 */
@Data
@ApiModel
public class CheckEmailDto implements Serializable {

	private static final long serialVersionUID = 3802825234063017559L;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long userId;

	/**
	 * 登录名
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
}
