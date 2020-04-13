/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;


import lombok.Data;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 字典主表
 * @Date: 14:20 2018/10/29
 */
@Data
public class DictPoJo extends BasePoJo {
    /**
     * 值
     */
    private String value;
    /**
     * 名称
     */
    private String label;
    /**
     * 子值集合
     */
    private List<DictItemPoJo> dictItemPoJos;
}
