/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * The class Uac menu check name dto.
 */
@Data
@ApiModel(value = "MenuCheckNameDto")
public class MenuCheckNameDto implements Serializable {
	private static final long serialVersionUID = -2372025597901225230L;
	/**
	 * 菜单的id
	 */
	@ApiModelProperty(value = "菜单的id")
	private Long menuId;
	/**
	 * 菜单的pid
	 */
	@ApiModelProperty(value = "上级菜单ID")
	@NotBlank(message = "上级菜单ID不能为空")
	private Long pid;
	/**
	 * 菜单的menuName
	 */
	@ApiModelProperty(value = "菜单名称")
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;


}
