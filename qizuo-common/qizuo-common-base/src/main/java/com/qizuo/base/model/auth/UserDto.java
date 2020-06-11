/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/** @Author: fangl @Description: 用户 @Date: 14:20 2018/10/29 */
@Data
public class UserDto {
  private String baseId;
  private Long baseIdL;
  /** 登录名 */
  private String userName;
  /** 密码 */
  private String passWord;
  /** 密码盐 */
  private String salt;
  /** 权限拼接串 */
  private String roleIds;
  /** 名称 */
  private String name;
  /** 性别 */
  private String sexCd;

  private String sexNm;
  /** 身份证 */
  private String idCard;
  /** 电话 */
  private String phone;
  /** 邮件 */
  private String email;
  /** 照片 */
  private String photo;
  /** 住址 */
  private String address;
  /** 登录时间 */
  private String loginDate;
  /** KEY(第三方) */
  private String outMutualKey;
  /** 组织Id */
  private String groupId;
  /** 组织Id */
  private Long groupIdL;
  /** 组织名称 */
  private String groupName;
  /** 状态 */
  private String status;
  /** 权限 */
  private Collection<GrantedAuthority> grantedAuthorities;
}
