/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.model.service.BaseController;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 二维码制造. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/make/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "Qrcode-MakeController.java", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QrcodeMakeController extends BaseController {
}
