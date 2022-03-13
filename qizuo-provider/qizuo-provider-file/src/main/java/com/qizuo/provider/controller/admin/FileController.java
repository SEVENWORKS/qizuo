/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** 上传文件查询. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/file/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "File-FileController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileController extends BaseController {
  @Autowired private FileService fileService;

  /**
   * @author: fangl
   * @description: 文件列表
   * @date: 15:45 2019/1/8
   */
  @PostMapping("list")
  @ApiOperation(httpMethod = "POST", value = "文件列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult list(@RequestBody FilePoJo filePoJo) {
    return BackResultUtils.ok(fileService.qList(filePoJo));
  }

  /**
   * @author: fangl
   * @description: 文件查询分页
   * @date: 15:45 2019/1/8
   */
  @PostMapping("page")
  @ApiOperation(httpMethod = "POST", value = "文件查询分页")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult page(@RequestBody PageDto<FilePoJo> poJos) {
    return BackResultUtils.ok(fileService.qPageQZ(poJos));
  }

  /**
   * @author: fangl
   * @description: 单个文件
   * @date: 16:14 2019/1/9
   */
  @PostMapping("query")
  @ApiOperation(httpMethod = "POST", value = "单个文件")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult query(@RequestBody FilePoJo filePoJo) {
    return BackResultUtils.ok(fileService.query(filePoJo));
  }
}
