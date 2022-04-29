/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.hystrix.FileApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * menu feign <br>
 * api.OAuth2FeignAutoConfigurationY主要配置rpc的时候也是需要token认证的，要不然springsecurity也会报错401，即需要带上验证token的header
 * 这地方除了验证token的header之外，还需要自定义的随机header处理，要不然验证也是过不去的 <br>
 * 最终方案舍弃了之前paascloud的配置，改成取自己的现有的header，具体方案直接到OAuth2FeignRequestInterceptor拦截器中看就行了
 */
@FeignClient(
  value = "qizuo-provider-file",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = FileApiHystrix.class,
  path = "/file"
)
public interface FileApi {

  /**
   * 列表
   *
   * @param filePoJo
   * @return
   */
  @PostMapping(value = "/file/file/list")
  BackResult list(@RequestBody FilePoJo filePoJo);

  /**
   * 分页
   *
   * @param poJos
   * @return
   */
  @PostMapping(value = "/file/file/page")
  BackResult page(@RequestBody PageDto<FilePoJo> poJos);

  /**
   * 单个
   *
   * @param filePoJo
   * @return
   */
  @PostMapping(value = "/file/file/query")
  BackResult query(@RequestBody FilePoJo filePoJo);
}
