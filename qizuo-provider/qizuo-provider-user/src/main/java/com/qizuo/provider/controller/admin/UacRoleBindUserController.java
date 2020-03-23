/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UacRoleBindUserController.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.model.LoginAuthDto;
import com.qizuo.core.annotation.LogAnnotation;
import com.qizuo.core.support.BaseController;
import com.qizuo.provider.model.dto.role.RoleBindUserDto;
import com.qizuo.provider.model.dto.role.RoleBindUserReqDto;
import com.qizuo.provider.service.UacRoleService;
import com.qizuo.base.model.wrapper.WrapMapper;
import com.qizuo.base.model.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色绑定用户.
 *
 * @author paascloud.net @gmail.com
 */
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UacRoleBindUserController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacRoleBindUserController extends BaseController {

	@Resource
	private UacRoleService uacRoleService;

	/**
	 * 角色绑定用户.
	 *
	 * @param roleBindUserReqDto the role bind user req dto
	 *
	 * @return the wrapper
	 */
	@LogAnnotation
	@PostMapping(value = "/bindUser")
	@ApiOperation(httpMethod = "POST", value = "角色绑定用户")
	public Wrapper bindUser(@ApiParam(name = "uacRoleBindUserReqDto", value = "角色绑定用户") @RequestBody RoleBindUserReqDto roleBindUserReqDto) {
		logger.info("roleBindUser={}", roleBindUserReqDto);
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		uacRoleService.bindUser4Role(roleBindUserReqDto, loginAuthDto);
		return WrapMapper.ok();
	}

	/**
	 * 获取角色绑定用户页面数据.
	 *
	 * @param roleId the role id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/getBindUser/{roleId}")
	@ApiOperation(httpMethod = "POST", value = "获取角色绑定用户页面数据")
	public Wrapper<RoleBindUserDto> getBindUser(@ApiParam(name = "roleId", value = "角色id") @PathVariable Long roleId) {
		logger.info("获取角色绑定用户页面数据. roleId={}", roleId);
		LoginAuthDto loginAuthDto = super.getLoginAuthDto();
		Long currentUserId = loginAuthDto.getUserId();
		RoleBindUserDto bindUserDto = uacRoleService.getRoleBindUserDto(roleId, currentUserId);
		return WrapMapper.ok(bindUserDto);
	}
}
