/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.service;

import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.utils.UserUtil;
import com.qizuo.security.model.SecurityUser;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** common user service. 这个和认证服务器不同的，认证服务器是自己的，这个地方主要是资源服务器用到的，后续用到要重写 */
@Configuration
@Data
public class SecurityUserDetailsSevice implements UserDetailsService {
  // 返回user对象
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto user = UserUtil.qUser();
    if (user == null) {
      throw new BadCredentialsException("用户名不存在或者密码错误");
    }
    //    Collection<GrantedAuthority> grantedAuthorities;权限暂时不加
    return new SecurityUser(
        user.getBaseIdL() + "",
        user.getUserName(),
        user.getPassWord(),
        user.getName(),
        user.getGroupIdL() + "",
        user.getGroupName(),
        user.getStatus());
  }
}
