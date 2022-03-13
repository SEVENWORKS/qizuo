/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.RolePoJo;
import com.qizuo.provider.service.RoleFeignApi;
import com.qizuo.provider.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/** rpc调用接口. */
@RestController
@Api(value = "API-RoleFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleFeignClient extends BaseController implements RoleFeignApi {
  @Autowired private RoleService roleService;

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询角色列表")
  public BackResult list(RolePoJo rolePoJo) {
    return BackResultUtils.ok(roleService.qList(rolePoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询单个角色")
  public BackResult query(RolePoJo rolePoJo) {
    return BackResultUtils.ok(roleService.query(rolePoJo));
  }
}
