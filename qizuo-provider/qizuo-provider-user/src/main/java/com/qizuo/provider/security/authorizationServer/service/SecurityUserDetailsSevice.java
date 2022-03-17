/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.authorizationServer.service;

import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.utils.UserUtil;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserService;
import com.qizuo.security.model.SecurityUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** common user service. 这个好像采用密码登录才会用到，暂时系统用的是客户端的方式，不会用到这个 */
@Configuration
@Data
public class SecurityUserDetailsSevice implements UserDetailsService {
  @Autowired
  UserService userService;
  // 返回user对象
  @SqlDisplay
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //根据名称查询用户信息
    UserPoJo userPoJo=new UserPoJo();
    userPoJo.setUserName(username);
    UserPoJo user=userService.qUserAllMsg(userPoJo);
    if (user == null) {
      throw new BadCredentialsException("用户名不存在或者密码错误");
    }
    Collection<GrantedAuthority> grantedAuthorities=getAuthorities(user);//获取权限
    return new SecurityUser(
        user.getBaseId(),
        user.getUserName(),
        user.getPassWord(),
        user.getName(),
        user.getGroupId() ,
        user.getGroupName(),
        user.getBaseStatus(),
        grantedAuthorities);
  }

  //权限获取
  private Collection<GrantedAuthority> getAuthorities(UserPoJo user){
    List<GrantedAuthority> authorities = new ArrayList<>();
    if(user.getRolePoJos().size()>0){
      user.getRolePoJos().forEach(key->{
        authorities.add(new SimpleGrantedAuthority(key.getName()));
      });
    }
    return authorities;
  }
}
