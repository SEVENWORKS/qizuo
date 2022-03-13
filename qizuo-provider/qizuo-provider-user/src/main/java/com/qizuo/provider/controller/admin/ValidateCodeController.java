/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.google.code.kaptcha.Producer;
import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.Thread.ThreadLocalMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** 验证码. */
@RequestMapping(
  value = "/validateCode/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-ValidateCodeController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ValidateCodeController extends BaseController {
  // 验证码生产对象
  @Resource Producer captchaProducer;
  // redis操作对象
  @Resource private RedisTemplate<String, Object> redisTemplate;
  /**
   * @author: fangl
   * @description: 图片验证码获取
   * @date: 16:25 2019/1/8
   */
  @RequestMapping("imgCodeGet")
  @ApiOperation(httpMethod = "POST", value = "图片验证码获取")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult imgCodeGet(HttpServletRequest request, HttpServletResponse response) {
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setContentType("image/jpeg");

    // create the text for the image
    String capText = captchaProducer.createText();
    // save
    String token = (String) ThreadLocalMap.get(GlobalConstant.SafeCode.TOKEN);
    redisTemplate
        .opsForValue()
        .set(token + "_imgCode", capText, GlobalConstant.SafeCode.TOKEN_TIME, TimeUnit.SECONDS);

    // create the image with the text
    try {
      BufferedImage bi = captchaProducer.createImage(capText);
      ServletOutputStream out = response.getOutputStream();
      ImageIO.write(bi, "jpg", out);
      try {
        out.flush();
      } finally {
        out.close();
      }
    } catch (IOException e) {
      throw new RuntimeException();
    }
    return BackResultUtils.ok();
  }
  /**
   * @author: fangl
   * @description: 图片验证码验证
   * @date: 16:25 2019/1/8
   */
  @RequestMapping("imgCodeCheck")
  @ApiOperation(httpMethod = "POST", value = "图片验证码验证")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult imgCheck(String imgCode) {
    // 获取缓存了验证码
    String token = (String) ThreadLocalMap.get(GlobalConstant.SafeCode.TOKEN);
    String code = (String) redisTemplate.opsForValue().get(token + "_imgCode");
    // 对比
    if (StringUtils.equals(imgCode, code)) {
      return BackResultUtils.ok();
    } else {
      return BackResultUtils.error();
    }
  }
}
