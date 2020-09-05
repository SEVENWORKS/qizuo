/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;

import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.service.hystrix.UserAdminApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/** user feign api. */
@FeignClient(
  value = "qizuo-provider-user-admin",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = UserAdminApiHystrix.class
)
public interface UserAdminFeignApi {}
