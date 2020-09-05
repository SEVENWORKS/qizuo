/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserFeignApi;
import com.qizuo.provider.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/** rpc调用接口. */
@RestController
@Api(value = "API-UserFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserFeignClient extends BaseController implements UserFeignApi {
  @Autowired private UserService userService;

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询用户列表")
  public BackResult list(UserPoJo userPoJo) {
    return BackResultUtils.ok(userService.qList(userPoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "获取用户分页列表")
  public BackResult page(PageDto<UserPoJo> poJos) {
    return BackResultUtils.ok(userService.qPageQZ(poJos));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询单个用户")
  public BackResult query(UserPoJo userPoJo) {
    return BackResultUtils.ok(userService.query(userPoJo));
  }
}
