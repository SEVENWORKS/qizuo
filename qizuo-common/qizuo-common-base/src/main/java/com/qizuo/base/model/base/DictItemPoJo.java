/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;

import lombok.Data;

/**
 * @Author: fangl
 * @Description: 字典子表
 * @Date: 14:20 2018/10/29
 */
@Data
public class DictItemPoJo extends BasePoJo {
    /**
     * 值
     */
    private String value;
    /**
     * 名称
     */
    private String label;
    /**
     * 主字典表ID
     */
    private String dictId;
    /**
     * 主字典表值
     */
    private String dictValue;
}
