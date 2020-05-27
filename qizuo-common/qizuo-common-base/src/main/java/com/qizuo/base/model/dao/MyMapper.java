/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * mybatis mapper. mybatis增强.
 * 后续可以利用集成baseDao和实现mymapper interface接口方式
 * mymapper是一个很方便东西
 * 如果因为表中没有实体中对应的字段而出现错误Caused by: java.lang.IllegalStateException: No typehandler found for property xxx
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
