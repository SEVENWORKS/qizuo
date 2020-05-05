/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;


import com.qizuo.base.model.service.BaseController;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录.
 */
@RestController
@RequestMapping(value = "/login")
//swagger
@Api(value = "User-LoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController extends BaseController {
//	@Resource

//	@PostMapping(value = "")
//	@LogAnnotation
//	@ApiOperation(httpMethod = "POST", value = "用户修改密码")
}
