/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.service;

import com.qizuo.provider.security.model.SecurityUser;
import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.*;

/**
 * common user service.
 */
@Configuration
@Data
public class SecurityUserDetailsSevice implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			userDetails = new SecurityUser();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}
}
