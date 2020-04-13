/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.menu;

import com.qizuo.provider.model.po.MenuPoJo;
import lombok.Data;

import java.io.Serializable;

/**
 * The class User menu children dto.
 */
@Data
public class MenuChildrenDto implements Serializable {
	private static final long serialVersionUID = -6279523061450477189L;
	/**
	 * 叶子节点菜单名称
	 */
	private String leafMenuName;
	/**
	 * 是否被选中,true
	 */
	private boolean checked;
	/**
	 * 跳转URL
	 */
	private String url;
	/**
	 * 叶子节点菜单Id
	 */
	private String leafMenuId;


	/**
	 * Instantiates a new User menu children dto.
	 */
	public MenuChildrenDto(MenuPoJo menuPoJo) {
		this.leafMenuName = menuPoJo.getName();
		this.url = menuPoJo.getUrl();
		this.leafMenuId = menuPoJo.getBaseId();
	}
}
