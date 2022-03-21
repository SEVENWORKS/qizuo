/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.MsgPoJo;
import com.qizuo.provider.model.po.RolePoJo;
import com.qizuo.provider.service.MsgFeignApi;
import com.qizuo.provider.service.MsgService;
import com.qizuo.provider.service.RoleFeignApi;
import com.qizuo.provider.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/** rpc调用接口. */
@RestController
@Api(value = "API-MsgFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MsgFeignClient extends BaseController implements MsgFeignApi {
  @Autowired private MsgService msgService;

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询消息列表")
  public BackResult list(MsgPoJo msgPoJo) {
    return BackResultUtils.ok(msgService.qList(msgPoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询单个消息")
  public BackResult query(MsgPoJo msgPoJo) {
    return BackResultUtils.ok(msgService.query(msgPoJo));
  }
}
