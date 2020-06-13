/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.utils;

import com.qizuo.base.model.auth.UserDto;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.Thread.ThreadLocalMap;

/** @Author :fangl @Description: user工具 @Date:14:12 2018/10/29 */
public class UserUtil {
  /**
   * @author: fangl
   * @description: 通过token获取用户信息
   * @date: 17:33 2019/1/9
   */
  public static UserDto qUser() {
    // 先获取第三方user
    UserDto userPoJo = (UserDto) ThreadLocalMap.get(GlobalConstant.Role.COMMON_USER);
    return userPoJo;
  }
}
