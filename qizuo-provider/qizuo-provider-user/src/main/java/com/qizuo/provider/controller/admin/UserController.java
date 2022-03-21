/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.base.utils.UserUtil;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserService;
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

/** 用户信息查询. */
@RequestMapping(
  value = "/user/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-UserController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//开启前置权限判断
//开启参数认证
@Validated
public class UserController extends BaseController {
  @Autowired private UserService userService;

  /**
   * @author: fangl
   * @description: 用户分页列表
   * @date: 15:02 2019/1/9
   */
  @RequestMapping("page")
  @ApiOperation(httpMethod = "POST", value = "用户分页列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult page(@RequestBody PageDto<UserPoJo> poJos) {
    return BackResultUtils.ok(userService.qPageQZ(poJos));
  }

  /**
   * @author: fangl
   * @description: 用户分页列表
   * @date: 15:02 2019/1/9
   */
  @RequestMapping("list")
  @ApiOperation(httpMethod = "POST", value = "用户分页列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult list(@RequestBody(required = false) UserPoJo userPoJo) {
    return BackResultUtils.ok(userService.qList(userPoJo));
  }

  /**
   * @author: fangl
   * @description: 查找单个
   * @date: 9:07 2019/2/13
   */
  @RequestMapping("single")
  @ApiOperation(httpMethod = "POST", value = "查找单个")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult query() {
    return BackResultUtils.ok(UserUtil.qUser());
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
  public BackResult query(@RequestBody UserPoJo userPoJo) {
    return BackResultUtils.ok(userService.query(userPoJo));
  }

  /**
   * @author: fangl
   * @description: 增加或者修改用户
   * @date: 15:02 2019/1/9
   */
  @RequestMapping("iuDo")
  @ApiOperation(httpMethod = "POST", value = "增加或者修改用户")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
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
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult delete(@RequestBody UserPoJo userPoJo) {
    userPoJo.setBaseStatus(GlobalConstant.STATUS_NO);
    userService.uStatus(userPoJo);
    return BackResultUtils.ok();
  }
}
