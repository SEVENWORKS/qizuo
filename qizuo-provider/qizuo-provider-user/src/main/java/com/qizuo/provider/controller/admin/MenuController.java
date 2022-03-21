package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.MenuPoJo;
import com.qizuo.provider.service.MenuFeignApi;
import com.qizuo.provider.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fangl
 * @description: 菜单控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(
  value = "/menu/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@Api(value = "User-MenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//开启前置权限判断
//开启参数认证
@Validated
public class MenuController {
  @Autowired private MenuService menuService;
  // rpc调用示例，即引入这个依赖后，直接注入，然后在方法中调用即可(最终会调用到对应服务的rpc controller中)
  @Autowired private MenuFeignApi menuFeignApi;

  /**
   * @author: fangl
   * @description: 获取相应菜单列表
   * @date: 17:10 2019/1/8
   */
  @PostMapping("list")
  @ApiOperation(httpMethod = "POST", value = "获取相应菜单列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  // @RequestBody是前台传递json数据才需要的，即使用之后会被视图解析器按照json方式解析，即我们自定义实现的jackson转的那个视图
  // @RequestBody流只能被读取一次，即一个方法上只能用一次
  // 对象的参数认证，必须在这个对象参数前加上@Validated
  public BackResult list(@RequestBody(required = false) @Validated MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.qList(menuPoJo));
  }

  /**
   * @author: fangl
   * @description: 根据用户获取菜单递归列表
   * @date: 17:10 2019/1/8
   */
  @RequestMapping("qEachList")
  @ApiOperation(httpMethod = "POST", value = "根据用户获取菜单递归列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult qEachList(@RequestBody MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.qEachList(menuPoJo));
  }

  /**
   * @author: fangl
   * @description: 菜单分页列表
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("page")
  @ApiOperation(httpMethod = "POST", value = "菜单分页列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult page(@RequestBody PageDto<MenuPoJo> pagePoJo) {
    return BackResultUtils.ok(menuService.qPageQZ(pagePoJo));
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
  public BackResult query(@RequestBody MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.query(menuPoJo));
  }

  /**
   * @author: fangl
   * @description: 菜单新增或者修改
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("iuDo")
  @ApiOperation(httpMethod = "POST", value = "菜单新增或者修改")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult iuDo(@RequestBody MenuPoJo menuPoJo) {
    // rpc调用示例
    // BackResult backResult = menuFeignApi.list(menuPoJo);
    if (StringUtils.isBlank(menuPoJo.getBaseId())) {
      // 插入
      menuService.insert(menuPoJo);
    } else {
      // 更新
      menuService.update(menuPoJo);
    }
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 菜单删除
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("delete")
  @ApiOperation(httpMethod = "POST", value = "菜单删除")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public BackResult delete(@RequestBody MenuPoJo menuPoJo) {
    menuService.delete(menuPoJo);
    return BackResultUtils.ok();
  }
}
