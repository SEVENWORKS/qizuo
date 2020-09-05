/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.menu;

import com.qizuo.provider.model.po.MenuPoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/** The class User menu children dto. */
@Data
@ApiModel(value = "MenuChildrenDto")
public class MenuChildrenDto implements Serializable {
  private static final long serialVersionUID = -6279523061450477189L;
  /** 叶子节点菜单名称 */
  @ApiModelProperty(value = "叶子节点菜单名称", required = true)
  private String leafMenuName;
  /** 是否被选中,true */
  @ApiModelProperty(value = "是否被选中,true", required = true)
  private boolean checked;
  /** 跳转URL */
  @ApiModelProperty(value = "跳转URL", required = true)
  private String url;
  /** 叶子节点菜单Id */
  @ApiModelProperty(value = "叶子节点菜单Id", required = true)
  private String leafMenuId;

  /** Instantiates a new User menu children dto. */
  public MenuChildrenDto(MenuPoJo menuPoJo) {
    this.leafMenuName = menuPoJo.getName();
    this.url = menuPoJo.getUrl();
    this.leafMenuId = menuPoJo.getBaseId();
  }
}
