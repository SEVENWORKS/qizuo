/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.authorizationServer.service;

import com.qizuo.security.model.SecurityUser;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** common user service. */
@Configuration
@Data
public class SecurityServerUserDetailsSevice implements UserDetailsService {
  // 返回user对象
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    UserDto user = UserUtil.loadUserByUsername(username);
    //    if (user == null) {
    //      throw new BadCredentialsException("用户名不存在或者密码错误");
    //    }
    //    Collection<GrantedAuthority> grantedAuthorities;权限暂时不加
    //    return new SecurityUser(
    //        user.getBaseIdL(),
    //        user.getUserName(),
    //        user.getPassWord(),
    //        user.getName(),
    //        user.getGroupIdL(),
    //        user.getGroupName());
    return new SecurityUser(1L, "qizuo", "qizuo", "qizuo", 1L, "qizuo");
  }
}
