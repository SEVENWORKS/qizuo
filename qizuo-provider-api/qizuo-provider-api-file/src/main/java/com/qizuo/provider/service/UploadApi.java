/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.hystrix.UploadApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/** role feign api. */
@FeignClient(
  value = "qizuo-provider-file",
  configuration = OAuth2FeignAutoConfigurationY.class,
  fallback = UploadApiHystrix.class
)
public interface UploadApi {

  /**
   * 单个文件上传
   *
   * @return
   */
  @PostMapping(value = "/file/upload/singleUpload")
  BackResult singleUpload(MultipartFile file);

  /**
   * 多文件上传
   *
   * @return
   */
  @PostMapping(value = "/file/upload/multiUpload")
  BackResult multiUpload(@RequestParam(value = "files") MultipartFile[] multipartFiles);

  /**
   * 文件下载
   *
   * @return
   */
  @PostMapping(value = "/file/upload/downFile")
  BackResult downFile(HttpServletResponse response, Model model, String resourceName);

  /**
   * 文件删除
   *
   * @param resourceName
   * @return
   */
  @PostMapping(value = "/file/upload/fileDelete")
  BackResult fileDelete(String resourceName);
}
