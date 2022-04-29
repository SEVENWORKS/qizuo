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

/** user feign api.注意带前缀的项目必须要加上path */
@FeignClient(
  value = "qizuo-provider-user",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = UserApiHystrix.class,
  path = "/user"
)
public interface UserFeignApi {
  /**
   * 列表
   *
   * @param userPoJo
   * @return
   */
  @PostMapping(value = "/user/list")
  BackResult list(@RequestBody UserPoJo userPoJo);

  /**
   * 分页
   *
   * @param poJos
   * @return
   */
  @PostMapping(value = "/user/page")
  BackResult page(@RequestBody PageDto<UserPoJo> poJos);

  /**
   * 单个
   *
   * @param userPoJo
   * @return
   */
  @PostMapping(value = "/user/query")
  BackResult query(@RequestBody UserPoJo userPoJo);

  /**
   * 根据名称查询用户信息
   *
   * @param userPoJo
   * @return
   */
  @PostMapping(value = "/user/qUserAllMsg")
  BackResult qUserAllMsg(@RequestBody UserPoJo userPoJo);
}
