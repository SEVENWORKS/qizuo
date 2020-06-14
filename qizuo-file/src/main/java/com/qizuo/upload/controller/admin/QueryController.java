/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.upload.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.page.PageDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 上传文件查询. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/query/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "File-QueryController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryController extends BaseController {
  @Autowired private FileService fileService;
  @Autowired private FileLogService fileLogService;
  /**
   * @author: fangl
   * @description: 文件查询(单个多个)
   * @date: 15:45 2019/1/8
   */
  @PostMapping("fileQueryList")
  @ApiOperation(httpMethod = "POST", value = "文件查询")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult fileQueryList(FilePoJo filePoJo) {
    return BackResultUtils.ok(fileService.qList(filePoJo));
  }

  /**
   * @author: fangl
   * @description: 文件查询分页
   * @date: 15:45 2019/1/8
   */
  @PostMapping("fileQueryPage")
  @ApiOperation(httpMethod = "POST", value = "文件查询分页")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult fileQueryPage(PageDto<FilePoJo> poJos, FilePoJo filePoJo) {
    poJos.setEntity(filePoJo);
    return BackResultUtils.ok(fileService.qPageQZ(poJos));
  }

  /**
   * @author: fangl
   * @description: 文件日志查询(多个单个)
   * @date: 16:14 2019/1/9
   */
  @PostMapping("fileLogQueryList")
  @ApiOperation(httpMethod = "POST", value = "文件日志查询")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult fileLogQueryList(FileLogPoJo fileLogPoJo) {
    return BackResultUtils.ok(fileLogService.qList(fileLogPoJo));
  }

  /**
   * @author: fangl
   * @description: 文件日志查询分页
   * @date: 16:14 2019/1/9
   */
  @PostMapping("fileLogQueryPage")
  @ApiOperation(httpMethod = "POST", value = "文件日志查询分页")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult fileLogQueryPage(PageDto<FileLogPoJo> poJos, FileLogPoJo fileLogPoJo) {
    poJos.setEntity(fileLogPoJo);
    return BackResultUtils.ok(fileLogService.qPageQZ(poJos));
  }
}
