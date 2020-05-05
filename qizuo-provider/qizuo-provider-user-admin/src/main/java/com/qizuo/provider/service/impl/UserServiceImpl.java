/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.service.impl;


import com.qizuo.base.model.service.BaseService;
import com.qizuo.provider.mapper.UserMapper;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * user service.
 */
@Service
//事务
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<UserMapper,UserPoJo> implements UserService {
//	@Resource
}
