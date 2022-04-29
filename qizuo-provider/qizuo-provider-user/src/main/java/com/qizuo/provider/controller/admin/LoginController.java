/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserService;
import com.qizuo.util.Thread.ThreadLocalMap;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.parse.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** 用户登录. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
/**
 * 常用注解： - @Api()用于类； 表示标识这个类是swagger的资源 controller上面<br>
 * - @ApiOperation()用于方法； 表示一个http请求的操作 controller单个方法<br>
 * - @ApiParam()用于方法，参数，字段说明； 表示对参数的添加元数据（说明或是否必填等） controller单个方法参数<br>
 * - @ApiModel()用于类 表示对类进行说明，用于参数用实体类接收 实体类<br>
 * - @ApiModelProperty()用于方法，字段 表示对model属性的说明或者数据操作更改 实体类参数<br>
 * - @ApiIgnore()用于类，方法，方法参数 表示这个方法或者类被忽略 <br>
 * - @ApiImplicitParam() 用于方法 表示单独的请求参数 <br>
 * - @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam<br>
 * 通常这些注解主要注释在controller和model层 <br>
 * @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值
 */
@RequestMapping(
  value = "/login/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@RefreshScope //注解能帮助我们做局部的参数刷新
// swagger
@Api(value = "User-LoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//开启前置权限判断
//开启参数校验：@Validated和@Valid两个用法相同，但是有些不太一样；
//这个注解用在Controller上，只能对方法上的对象以外的校验开启；如果针对对象的校验，这个注解必须用在对象前面；
//ValidationMessages.properties是参数校验不通过，返回message信息配置
@Validated
public class LoginController extends BaseController {
  // 管理员终极账号
  @Value("${qizuo}")
  private String qizuo;
  // redis操作对象
  @Resource private RedisTemplate<String, Object> redisTemplate;
  // userservice
  @Autowired private UserService userService;

//  @Autowired(原先直接做server可以有这个类，现在不是server用法后续研究)
//  @Qualifier("consumerTokenServices")
//  ConsumerTokenServices consumerTokenServices;
  /**
   * @author: fangl
   * @description: 用户登录
   * 操作日志打印；request参数验证；sql日志是否打印；不需要验证登录信息
   *
   * 为什么不把用户登录放到token获取成功时候，即security验证成功success handel中？因为security几种模式，最终就是安全验证，说白了就是token，这个和页面login是两回事。
   * 所以！就算是密码模式获取到token后，也必须暗中携带用户名密码到这个mapping下进行登录。
   * @date: 15:45 2019/1/8
   */
  @PostMapping("login")
  @ApiOperation(httpMethod = "POST", value = "用户登录")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @NoNeedAccessAuthentication
  @PreAuthorize("hasAuthority('ROLE_USER')")
  //@Size就是@Validated开启验证后可以用来参数认证@RequestParam(value = "key")@Size(min = 5,max = 100) String key
  //这个只有密码模式下的token才可以用到
  public BackResult login(@AuthenticationPrincipal String username) {
    // 存入token
    String token = (String) ThreadLocalMap.get(GlobalConstant.SafeCode.TOKEN);
    if (redisTemplate.opsForValue().get(token) != null) {
      return BackResultUtils.ok();
    } else {
      // 根据用户名获取key
      UserPoJo userPoJo = userService.qPasswordByName(username);
      UserDto userDto = new UserDto();
      // 普通用户添加
      userDto.setUserName(userPoJo.getUserName());
      userDto.setBaseId(userPoJo.getBaseId());
      userDto.setRoleIds(userPoJo.getRoleIds());
      userDto.setStatus(userPoJo.getBaseStatus());
      userDto.setIdCard(userPoJo.getIdCard());
      userDto.setPhoto(userPoJo.getPhoto());
      userDto.setRemarks(userPoJo.getBaseRemarks());
      // 塞入redis
      try {
        redisTemplate
            .opsForValue()
            .set(
                token,
                JacksonUtil.toJson(userDto),
                GlobalConstant.SafeCode.TOKEN_TIME,
                TimeUnit.SECONDS);
      } catch (IOException e) {
        return BackResultUtils.error();
      }
      return BackResultUtils.ok();
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
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult logOut() {
    String token = (String) ThreadLocalMap.get(GlobalConstant.SafeCode.TOKEN);
    //oauth2失效token(下面这个对jwttoken没用，也就是下面方法即使撤销返回成功，但是下次携带jwttoken还是可以访问，是通病；所以采取第二种，redis校验用户是否存在，即后面删除用户信息这段)
    //consumerTokenServices.revokeToken(token);
    // 删除用户信息
    if (redisTemplate.opsForValue().get(token) != null) {
      redisTemplate.delete(token);
    }
    return BackResultUtils.ok();
  }
}
