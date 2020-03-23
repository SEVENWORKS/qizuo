/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改状态载体
 */
@Data
@ApiModel(value = "更改状态")
public class UpdateStatusDto implements Serializable {

	private static final long serialVersionUID = 1494899235149813850L;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID")
	private String id;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private Integer status;
}
