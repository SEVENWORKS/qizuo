/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/** 用户安全信息体 配合不同的service. */
@Configuration
@Data
public class SecurityUser implements UserDetails {
  private static final long serialVersionUID = 4872628781561412463L;

  private static final String ENABLE = "ENABLE";

  /** 已授予的权限 */
  private Collection<GrantedAuthority> authorities;

  private Long userId;

  private String nickName;

  private String loginName;

  private String loginPwd;

  private String status;

  private Long groupId;

  private String groupName;

  public SecurityUser() {}

  public SecurityUser(
      Long userId,
      String loginName,
      String loginPwd,
      String nickName,
      Long groupId,
      String groupName) {
    this.setUserId(userId);
    this.setLoginName(loginName);
    this.setLoginPwd(loginPwd);
    this.setNickName(nickName);
    this.setGroupId(groupId);
    this.setGroupName(groupName);
  }

  public SecurityUser(
      Long userId,
      String loginName,
      String loginPwd,
      String nickName,
      Long groupId,
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
    return StringUtils.equals(this.status, ENABLE);
  }
}