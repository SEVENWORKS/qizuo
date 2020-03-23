/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * mybatis mapper. mybatis增强
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
