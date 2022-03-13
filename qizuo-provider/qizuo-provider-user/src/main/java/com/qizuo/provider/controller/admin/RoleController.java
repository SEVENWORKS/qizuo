package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.RolePoJo;
import com.qizuo.provider.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fangl
 * @description: 角色控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(
  value = "/role/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-RoleController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {
  @Autowired private RoleService roleService;

  /**
   * @author: fangl
   * @description: 角色列表
   * @date: 17:10 2019/1/8
   */
  @RequestMapping("list")
  @ApiOperation(httpMethod = "POST", value = "角色列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult list(@RequestBody RolePoJo rolePoJo) {
    return BackResultUtils.ok(roleService.qList(rolePoJo));
  }

  /**
   * @author: fangl
   * @description: 查找单个
   * @date: 9:07 2019/2/13
   */
  @RequestMapping("query")
  @ApiOperation(httpMethod = "POST", value = "查找单个")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult query(@RequestBody RolePoJo rolePoJo) {
    return BackResultUtils.ok(roleService.query(rolePoJo));
  }
}
