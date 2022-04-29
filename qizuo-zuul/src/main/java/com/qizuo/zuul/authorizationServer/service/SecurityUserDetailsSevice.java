/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.authorizationServer.service;

import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserFeignApi;
import com.qizuo.security.model.SecurityUser;
import com.qizuo.util.parse.JacksonUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** common user service. 这个方法除了客户端模式之外，另外三种模式都会进入其中查询，查询成功后，返回给验证器验证 */
@Configuration
@Data
public class SecurityUserDetailsSevice implements UserDetailsService {
  @Autowired private UserFeignApi userFeignApi;
  // 返回user对象
  @SqlDisplay
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //根据名称查询用户信息
    UserPoJo userPoJo=new UserPoJo();
    userPoJo.setUserName(username);
    Object back=userFeignApi.qUserAllMsg(userPoJo).getResult();
    //返回的是Linkmap，需要进行转换
    UserPoJo user;
    try {
      user = JacksonUtil.parseJson(JacksonUtil.toJson(back), UserPoJo.class);
    } catch (IOException e) {
      user= null;
    }
    if (user == null) {
      throw new BadCredentialsException("用户名不存在或者密码错误");
    }
    //返回权限主体进行验证
    Collection<GrantedAuthority> grantedAuthorities=getAuthorities(user);
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
    if(null!=user.getRolePoJos()&&user.getRolePoJos().size()>0){
      user.getRolePoJos().forEach(key->{
        authorities.add(new SimpleGrantedAuthority(key.getName()));
      });
    }
    return authorities;
  }
}
