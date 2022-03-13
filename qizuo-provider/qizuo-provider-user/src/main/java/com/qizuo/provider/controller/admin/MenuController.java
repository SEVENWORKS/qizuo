package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.MenuPoJo;
import com.qizuo.provider.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class MenuController {
  @Autowired private MenuService menuService;

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
  // @RequestBody是前台传递json数据才需要的，即使用之后会被视图解析器按照json方式解析，即我们自定义实现的jackson转的那个视图
  // @RequestBody流只能被读取一次，即一个方法上只能用一次
  public BackResult list(@RequestBody MenuPoJo menuPoJo) {
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
  public BackResult query(@RequestBody MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.query(menuPoJo));
  }
}
