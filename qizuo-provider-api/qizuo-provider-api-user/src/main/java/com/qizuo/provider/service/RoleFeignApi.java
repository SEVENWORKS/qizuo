/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.model.po.RolePoJo;
import com.qizuo.provider.service.hystrix.RoleApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/** role feign api. */
@FeignClient(
  value = "qizuo-provider-user",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = RoleApiHystrix.class,
  path = "/user"
)
public interface RoleFeignApi {

  /**
   * 列表
   *
   * @param rolePoJo
   * @return
   */
  @PostMapping(value = "/user/role/list")
  BackResult list(@RequestBody RolePoJo rolePoJo);

  /**
   * 单个
   *
   * @param rolePoJo
   * @return
   */
  @PostMapping(value = "/user/role/query")
  BackResult query(@RequestBody RolePoJo rolePoJo);
}
