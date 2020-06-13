/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.model;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/** 用户安全信息体 配合不同的service. */
@Configuration
@Data
public class SecurityUser implements UserDetails {
  private static final long serialVersionUID = 4872628781561412463L;

  /** 已授予的权限 */
  private Collection<GrantedAuthority> authorities;

  private String userId;

  private String nickName;

  private String loginName;

  private String loginPwd;

  private String status;

  private String groupId;

  private String groupName;

  public SecurityUser() {}

  public SecurityUser(
      String userId,
      String loginName,
      String loginPwd,
      String nickName,
      String groupId,
      String groupName,
      String status) {
    this.setUserId(userId);
    this.setLoginName(loginName);
    this.setLoginPwd(loginPwd);
    this.setNickName(nickName);
    this.setGroupId(groupId);
    this.setGroupName(groupName);
    this.setStatus(status);
  }

  // 多了一个权限构造
  public SecurityUser(
      String userId,
      String loginName,
      String loginPwd,
      String nickName,
      String groupId,
      String groupName,
      String status,
      Collection<GrantedAuthority> grantedAuthorities) {
    this.setUserId(userId);
    this.setLoginName(loginName);
    this.setLoginPwd(loginPwd);
    this.setNickName(nickName);
    this.setGroupId(groupId);
    this.setGroupName(groupName);
    this.setStatus(status);
    this.authorities = grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return this.getLoginPwd();
  }

  @Override
  public String getUsername() {
    return this.getLoginName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.status == GlobalConstant.STATUS_YES;
  }
}
