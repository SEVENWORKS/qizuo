/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dao.mapper;

import com.qizuo.base.model.dao.BaseDao;
import com.qizuo.provider.model.po.UserPoJo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/** user mapper. */
@Mapper
@Component
public interface UserMapper extends BaseDao<UserPoJo> {}
