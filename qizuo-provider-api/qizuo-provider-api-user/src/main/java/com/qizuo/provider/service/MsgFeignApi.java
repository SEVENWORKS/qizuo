/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.model.po.MsgPoJo;
import com.qizuo.provider.service.hystrix.MsgApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/** role feign api. */
@FeignClient(
  value = "qizuo-provider-user",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = MsgApiHystrix.class
)
public interface MsgFeignApi {

  /**
   * 列表
   *
   * @param msgPoJo
   * @return
   */
  @PostMapping(value = "/user/msg/list")
  BackResult list(@RequestBody MsgPoJo msgPoJo);

  /**
   * 单个
   *
   * @param msgPoJo
   * @return
   */
  @PostMapping(value = "/user/msg/query")
  BackResult query(@RequestBody MsgPoJo msgPoJo);
}
