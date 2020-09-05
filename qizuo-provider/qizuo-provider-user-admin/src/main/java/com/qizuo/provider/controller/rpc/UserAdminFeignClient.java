/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.service.BaseController;
import com.qizuo.provider.service.UserAdminFeignApi;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/** rpc调用接口. */
@RestController
@Api(value = "API-UserAdminFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAdminFeignClient extends BaseController implements UserAdminFeignApi {}
