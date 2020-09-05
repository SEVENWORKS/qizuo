/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** The class User menu dto. */
@Data
@ApiModel(value = "MenuDto")
public class MenuDto implements Serializable {
  private static final long serialVersionUID = -5220054739321565548L;
  /** 一级菜单名称 */
  @ApiModelProperty(value = "一级菜单名称", required = true)
  private String fistMenuName;
  /** 一级菜单图标 */
  @ApiModelProperty(value = "一级菜单图标", required = true)
  private String fistMenuIcon;
  /** 一级菜单Id */
  @ApiModelProperty(value = "一级菜单Id", required = true)
  private String fistMenuId;
  /** 所有的子节点 */
  @ApiModelProperty(value = "所有的子节点", required = true)
  private List<MenuChildrenDto> children;
}
