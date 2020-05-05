/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: fangl
 * @Description: 角色
 * @Date: 14:20 2018/10/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePoJo extends BasePoJo {
    /**
     * 名称
     */
    private String name;
    /**
     * 资源拼接串
     */
    private String menuIds;
    /**
     * 数据操作权限拼接串
     */
    private String dataScopeCds;
    /**
     * 首页路径
     */
    private String indexUrl;
    /**
     * 跳转路径
     */
    private String jumpUrl;
}
