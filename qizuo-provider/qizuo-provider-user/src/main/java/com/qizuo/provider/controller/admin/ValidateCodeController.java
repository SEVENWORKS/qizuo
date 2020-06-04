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

/** 验证码. */
@RequestMapping(
  value = "${qizuo.url_module}/validateCode/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-ValidateCodeController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ValidateCodeController extends BaseController {
  /**
   * @author: fangl
   * @description: 图片验证码验证
   * @date: 16:25 2019/1/8
   */
  @RequestMapping("imgCheck")
  @ApiOperation(httpMethod = "POST", value = "图片验证码验证")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult imgCheck(String imgCode) {
    return BackResultUtils.ok();
  }
}
