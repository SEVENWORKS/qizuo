/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Uac menu status dto.
 */
@Data
@ApiModel(value = "MenuStatusDto")
public class MenuStatusDto implements Serializable {
	private static final long serialVersionUID = 7834606418601316142L;
	/**
	 * 菜单的Id
	 */
	@ApiModelProperty(value = "菜单的Id", required = true)
	private Long id;
	/**
	 * 菜单的父Id
	 */
	@ApiModelProperty(value = "菜单的状态", required = true)
	private String status;

}
