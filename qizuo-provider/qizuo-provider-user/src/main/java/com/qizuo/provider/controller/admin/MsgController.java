/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.MsgPoJo;
import com.qizuo.provider.service.MsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 用户消息查询. */
@RequestMapping(
  value = "/msg/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-MsgController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//开启前置权限判断
//开启参数认证
@Validated
public class MsgController extends BaseController {
  @Autowired private MsgService msgService;

  /**
   * @author: fangl
   * @description: 消息列表
   * @date: 17:10 2019/1/8
   */
  @RequestMapping("list")
  @ApiOperation(httpMethod = "POST", value = "消息列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult list(@RequestBody(required = false) MsgPoJo msgPoJo) {
    return BackResultUtils.ok(msgService.qList(msgPoJo));
  }

  /**
   * @author: fangl
   * @description: 查找单个
   * @date: 9:07 2019/2/13
   */
  @RequestMapping("query")
  @ApiOperation(httpMethod = "POST", value = "查找单个")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult query(@RequestBody MsgPoJo msgPoJo) {
    return BackResultUtils.ok(msgService.query(msgPoJo));
  }

  /**
   * @author: fangl
   * @description: 消息新增或者修改
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("iuDo")
  @ApiOperation(httpMethod = "POST", value = "消息新增或者修改")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult iuDo(@RequestBody MsgPoJo msgPoJo) {
    if (StringUtils.isBlank(msgPoJo.getBaseId())) {
      // 插入
      msgService.insert(msgPoJo);
    } else {
      // 更新
      msgService.update(msgPoJo);
    }
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 消息删除
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("delete")
  @ApiOperation(httpMethod = "POST", value = "消息删除")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult delete(@RequestBody MsgPoJo msgPoJo) {
    msgService.delete(msgPoJo);
    return BackResultUtils.ok();
  }
}
