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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** token. */
@RequestMapping(
  value = "${qizuo.url_module}/token/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-TokenController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TokenController extends BaseController {
  /**
   * @author: fangl
   * @description: 获取token
   * @date: 16:14 2019/1/9
   */
  @RequestMapping("getToken")
  @ApiOperation(httpMethod = "POST", value = "获取token")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult getToken() {
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 刷新token
   * @date: 16:14 2019/1/9
   */
  @RequestMapping("refreshToken")
  @ApiOperation(httpMethod = "POST", value = "刷新token")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult refreshToken() {
    return BackResultUtils.ok();
  }
}
