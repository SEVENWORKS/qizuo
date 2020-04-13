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
 * 验证码.
 */
@RestController
@RequestMapping(value = "/validateCode", produces = {"application/json;charset=UTF-8"})
@Api(value = "User-ValidateCodeController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ValidateCodeController extends BaseController {
}
