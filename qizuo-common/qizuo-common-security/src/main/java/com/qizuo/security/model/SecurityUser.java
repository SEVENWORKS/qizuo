/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.security.model;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/** 用户安全信息体 配合不同的service. */
@Configuration
@Data
@ApiModel(value = "security安全实体")
public class SecurityUser implements UserDetails {
  private static final long serialVersionUID = 4872628781561412463L;

  /** 已授予的权限 */
  @ApiModelProperty(value = "性别")
  private Collection<GrantedAuthority> authorities;

  @ApiModelProperty(value = "用户id")
  private String userId;

  @ApiModelProperty(value = "昵称")
  private String nickName;

  @ApiModelProperty(value = "登录名称")
  private String loginName;

  @ApiModelProperty(value = "登录密码")
  private String loginPwd;

  @ApiModelProperty(value = "状态")
  private String status;

  @ApiModelProperty(value = "组织id")
  private String groupId;

  @ApiModelProperty(value = "组织名称")
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
    return this.status.equals(GlobalConstant.STATUS_YES);
  }
}
