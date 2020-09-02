/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 用户信息查询. */
@RequestMapping(
  value = "/user/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "UserAdmin-UserController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController {
  @Autowired private UserService userService;

  /**
   * @author: fangl
   * @description: 增加或者修改用户
   * @date: 15:02 2019/1/9
   */
  @RequestMapping("iuDo")
  @ApiOperation(httpMethod = "POST", value = "增加或者修改用户")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  public BackResult iuDo(@RequestBody UserPoJo userPoJo) {
    if (StringUtils.isBlank(userPoJo.getBaseId())) {
      // 插入
      userService.insert(userPoJo);
    } else {
      // 更新
      userService.update(userPoJo);
    }
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 删除用户
   * @date: 15:02 2019/1/9
   */
  @RequestMapping("delete")
  @ApiOperation(httpMethod = "POST", value = "删除用户")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult delete(@RequestBody UserPoJo userPoJo) {
    userPoJo.setBaseStatus(GlobalConstant.STATUS_NO);
    userService.uStatus(userPoJo);
    return BackResultUtils.ok();
  }
}
