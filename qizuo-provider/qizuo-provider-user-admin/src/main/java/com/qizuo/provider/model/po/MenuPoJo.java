/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 菜单
 * @Date: 14:20 2018/10/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuPoJo extends BasePoJo {
    /**
     * 名称
     */
    private String name;
    /**
     * 跳转路径
     */
    private String url;
    /**
     * 父ID
     */
    private String parentId;
    /**
     * 主题
     */
    private String theme;
    /**
     * 子菜单集合
     */
    private List<MenuPoJo> menuPoJos;
}
