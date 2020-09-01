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
import com.qizuo.util.Thread.ThreadLocalMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/** 用户登录. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/login/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "User-LoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController extends BaseController {
  // 管理员终极账号
  @Value("${qizuo}")
  private String qizuo;
  // redis操作对象
  @Resource private RedisTemplate<String, Object> redisTemplate;
  /**
   * @author: fangl
   * @description: 用户登录
   * @date: 15:45 2019/1/8
   */
  @PostMapping("login")
  @ApiOperation(httpMethod = "POST", value = "用户登录")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult login(@RequestParam(value = "key") String key) {
    // 存入token
    String token = (String) ThreadLocalMap.get(GlobalConstant.SafeCode.TOKEN);
    if (redisTemplate.opsForValue().get(token) != null) {
      return BackResultUtils.ok();
    } else {
      if (StringUtils.equals(key, qizuo)) {
        redisTemplate
            .opsForValue()
            .set(token, key, GlobalConstant.SafeCode.TOKEN_TIME, TimeUnit.SECONDS);
        return BackResultUtils.ok();
      } else {
        return BackResultUtils.error();
      }
    }
  }

  /**
   * @author: fangl
   * @description: 登出
   * @date: 16:14 2019/1/9
   */
  @PostMapping("logout")
  @ApiOperation(httpMethod = "POST", value = "用户登出")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  public BackResult logOut() {
    return BackResultUtils.ok();
  }
}
