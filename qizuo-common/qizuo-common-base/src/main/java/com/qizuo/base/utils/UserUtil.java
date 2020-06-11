/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.utils;

import com.qizuo.base.model.auth.UserDto;

/** @Author :fangl @Description: user工具 @Date:14:12 2018/10/29 */
public class UserUtil {
  /**
   * @author: fangl
   * @description: 获取用户信息(系统管理员user和第三方user肯定只会有一个存在)
   * @date: 17:33 2019/1/9
   */
  public static UserDto qUser() {
    // 先获取第三方user
    UserDto userPoJo = new UserDto();
    return userPoJo;
  }

  public static UserDto loadUserByUsername(String name) {
    // 先获取第三方user
    UserDto userPoJo = new UserDto();
    return userPoJo;
  }
}
