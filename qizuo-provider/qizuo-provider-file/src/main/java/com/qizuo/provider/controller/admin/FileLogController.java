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
import com.qizuo.provider.model.po.FileLogPoJo;
import com.qizuo.provider.service.FileLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** 上传文件查询. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/fileLog/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "File-FileLogController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//开启前置权限判断
//开启参数认证
@Validated
public class FileLogController extends BaseController {
  @Autowired private FileLogService fileLogService;
  /**
   * @author: fangl
   * @description: 列表
   * @date: 15:45 2019/1/8
   */
  @PostMapping("list")
  @ApiOperation(httpMethod = "POST", value = "列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_FILE')")
  public BackResult list(@RequestBody(required = false) FileLogPoJo fileLogPoJo) {
    return BackResultUtils.ok(fileLogService.qList(fileLogPoJo));
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
  @PreAuthorize("hasAuthority('ROLE_FILE')")
  public BackResult page(@RequestBody PageDto<FileLogPoJo> fileLogPoJo) {
    return BackResultUtils.ok(fileLogService.qPageQZ(fileLogPoJo));
  }

  /**
   * @author: fangl
   * @description: 文件日志查询(多个单个)
   * @date: 16:14 2019/1/9
   */
  @PostMapping("query")
  @ApiOperation(httpMethod = "POST", value = "文件日志查询")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_FILE')")
  public BackResult query(@RequestBody FileLogPoJo fileLogPoJo) {
    return BackResultUtils.ok(fileLogService.query(fileLogPoJo));
  }

}
