/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.upload.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.upload.model.po.FileLogPoJo;
import com.qizuo.upload.model.po.FilePoJo;
import com.qizuo.upload.service.FileLogService;
import com.qizuo.upload.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/** 文件上传下载. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/upload/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "File-UploadController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadController extends BaseController {
  @Autowired private FileService fileService;
  @Autowired private FileLogService fileLogService;
  /**
   * @author: fangl
   * @description: 单文件上传
   * @date: 15:45 2019/1/8
   */
  @PostMapping("singleUpload")
  @ApiOperation(httpMethod = "POST", value = "单文件上传")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult singleUpload(
      MultipartFile multipartFile, FilePoJo filePoJo, FileLogPoJo fileLogPoJo) {
    // 插入
    fileService.insert(filePoJo);
    fileLogService.insert(fileLogPoJo);
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 多文件上传
   * @date: 15:45 2019/1/8
   */
  @PostMapping("multiUpload")
  @ApiOperation(httpMethod = "POST", value = "多文件上传")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult multiUpload(
      @RequestParam(value = "files") MultipartFile[] multipartFiles,
      List<FilePoJo> filePoJos,
      List<FileLogPoJo> fileLogPoJos) {
    // 批量插入
    fileService.iAbatchInsert(filePoJos);
    fileLogService.iAbatchInsert(fileLogPoJos);
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 文件下载
   * @date: 16:14 2019/1/9
   */
  @PostMapping("downFile")
  @ApiOperation(httpMethod = "POST", value = "文件下载")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult downFile(FilePoJo filePoJo) {
    return BackResultUtils.ok(fileService.query(filePoJo));
  }

  /**
   * @author: fangl
   * @description: 文件删除(多个单个)
   * @date: 16:14 2019/1/9
   */
  @PostMapping("fileDelete")
  @ApiOperation(httpMethod = "POST", value = "文件删除")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult fileDelete(FilePoJo filePoJo, FileLogPoJo fileLogPoJo) {
    // 删除
    fileService.delete(filePoJo);
    // 状态修改
    fileLogService.uLogResult(fileLogPoJo);
    return BackResultUtils.ok();
  }
}
