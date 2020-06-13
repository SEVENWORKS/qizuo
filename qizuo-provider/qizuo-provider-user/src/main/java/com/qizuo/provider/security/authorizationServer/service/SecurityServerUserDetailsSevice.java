/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.authorizationServer.service;

import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserService;
import com.qizuo.security.model.SecurityUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** common user service. */
@Configuration
@Data
public class SecurityServerUserDetailsSevice implements UserDetailsService {
  @Autowired private UserService userService;
  // 根据用户名获取用户对象
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserPoJo user = userService.qPasswordByName(username);
    if (user == null) {
      throw new BadCredentialsException("用户名不存在或者密码错误");
    }
    //        Collection<GrantedAuthority> grantedAuthorities;//权限暂时不加
    return new SecurityUser(
        user.getBaseId(),
        user.getUserName(),
        user.getPassWord(),
        user.getName(),
        user.getGroupId(),
        user.getGroupName(),
        user.getBaseStatus());
  }
}
