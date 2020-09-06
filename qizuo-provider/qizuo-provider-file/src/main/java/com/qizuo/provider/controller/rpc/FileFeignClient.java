/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.FileApi;
import com.qizuo.provider.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/** rpc调用接口. */
@RestController
@Api(value = "API-FileFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileFeignClient extends BaseController implements FileApi {
  @Autowired private FileService fileService;

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询文件列表")
  public BackResult list(FilePoJo filePoJo) {
    return BackResultUtils.ok(fileService.qList(filePoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询文件分页")
  public BackResult page(PageDto<FilePoJo> poJos) {
    return BackResultUtils.ok(fileService.qPageQZ(poJos));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询单个文件")
  public BackResult query(FilePoJo filePoJo) {
    return BackResultUtils.ok(fileService.query(filePoJo));
  }
}
