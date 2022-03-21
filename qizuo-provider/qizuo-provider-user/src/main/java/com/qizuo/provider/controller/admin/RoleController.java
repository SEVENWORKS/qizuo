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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
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
//开启前置权限判断
//开启参数认证
@Validated
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
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult list(@RequestBody(required = false) RolePoJo rolePoJo) {
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
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult query(@RequestBody RolePoJo rolePoJo) {
    return BackResultUtils.ok(roleService.query(rolePoJo));
  }

  /**
   * @author: fangl
   * @description: 角色新增或者修改
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("iuDo")
  @ApiOperation(httpMethod = "POST", value = "角色新增或者修改")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult iuDo(@RequestBody RolePoJo rolePoJo) {
    if (StringUtils.isBlank(rolePoJo.getBaseId())) {
      // 插入
      roleService.insert(rolePoJo);
    } else {
      // 更新
      roleService.update(rolePoJo);
    }
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 角色删除
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("delete")
  @ApiOperation(httpMethod = "POST", value = "角色删除")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult delete(@RequestBody RolePoJo rolePoJo) {
    roleService.delete(rolePoJo);
    return BackResultUtils.ok();
  }
}
