/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.service;

import com.qizuo.provider.security.model.SecurityUser;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** common user service. */
@Configuration
@Data
public class SecurityUserDetailsSevice implements UserDetailsService {
  // 返回user对象
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails userDetails = null;
    try {
      userDetails = new SecurityUser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userDetails;
  }
}
