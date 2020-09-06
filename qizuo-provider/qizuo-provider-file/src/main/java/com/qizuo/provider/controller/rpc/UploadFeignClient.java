/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.FileLogService;
import com.qizuo.provider.service.FileService;
import com.qizuo.provider.service.UploadApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/** rpc调用接口. 暂不实现rpc上传.*/
@RestController
@Api(value = "API-UploadFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadFeignClient extends BaseController implements UploadApi {
  @Autowired private FileService fileService;
  @Autowired private FileLogService fileLogService;

  @Override
  @ApiOperation(httpMethod = "POST", value = "单个文件上传")
  public BackResult singleUpload(MultipartFile file) {
    return null;
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "多个文件上传")
  public BackResult multiUpload(MultipartFile[] multipartFiles) {
    return null;
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "文件下载")
  public BackResult downFile(HttpServletResponse response, Model model, String resourceName) {
    return null;
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "文件删除")
  public BackResult fileDelete(String resourceName) {
    return null;
  }
}
