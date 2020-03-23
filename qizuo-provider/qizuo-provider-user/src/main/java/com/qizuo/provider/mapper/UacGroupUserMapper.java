/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UacGroupUserMapper.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.qizuo.provider.mapper;

import com.qizuo.core.mybatis.MyMapper;
import com.qizuo.provider.model.domain.UacGroup;
import com.qizuo.provider.model.domain.UacGroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Uac group user mapper.
 *
 * @author paascloud.net@gmail.com
 */
@Mapper
@Component
public interface UacGroupUserMapper extends MyMapper<UacGroupUser> {

	/**
	 * Query by user id uac group user.
	 *
	 * @param userId the user id
	 *
	 * @return the uac group user
	 */
	UacGroupUser getByUserId(Long userId);

	/**
	 * Update by user id int.
	 *
	 * @param uacGroupUser the uac group user
	 *
	 * @return the int
	 */
	int updateByUserId(UacGroupUser uacGroupUser);

	/**
	 * Select group list by user id list.
	 *
	 * @param userId the user id
	 *
	 * @return the list
	 */
	List<UacGroup> selectGroupListByUserId(Long userId);

	/**
	 * List by group id list.
	 *
	 * @param groupId the group id
	 *
	 * @return the list
	 */
	List<UacGroupUser> listByGroupId(@Param("groupId") Long groupId);

	/**
	 * Delete exclude super mng int.
	 *
	 * @param groupId            the group id
	 * @param superManagerRoleId the super manager role id
	 *
	 * @return the int
	 */
	int deleteExcludeSuperMng(@Param("currentGroupId") Long groupId, @Param("superManagerRoleId") Long superManagerRoleId);
}