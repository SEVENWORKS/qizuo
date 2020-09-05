/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.hystrix.UserApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/** user feign api. */
@FeignClient(
  value = "qizuo-provider-user",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = UserApiHystrix.class
)
public interface UserFeignApi {
  /**
   * 列表
   *
   * @param userPoJo
   * @return
   */
  @PostMapping(value = "/user/user/list")
  BackResult list(@RequestBody UserPoJo userPoJo);

  /**
   * 分页
   *
   * @param poJos
   * @return
   */
  @PostMapping(value = "/user/user/page")
  BackResult page(@RequestBody PageDto<UserPoJo> poJos);

  /**
   * 但跟
   *
   * @param userPoJo
   * @return
   */
  @PostMapping(value = "/user/user/query")
  BackResult query(@RequestBody UserPoJo userPoJo);
}
