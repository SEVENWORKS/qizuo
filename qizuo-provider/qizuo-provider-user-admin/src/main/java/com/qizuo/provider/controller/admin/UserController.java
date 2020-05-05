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
 * 用户信息查询.
 */
@RestController
@RequestMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
@Api(value = "User-UserController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController {
}
