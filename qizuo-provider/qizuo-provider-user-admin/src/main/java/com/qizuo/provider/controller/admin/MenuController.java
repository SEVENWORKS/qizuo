package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "UserAdmin-MenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController {
  @Autowired private MenuService menuService;
  // rpc调用示例
  @Autowired private MenuFeignApi menuFeignApi;
  /**
   * @author: fangl
   * @description: 菜单新增或者修改
   * @date: 15:04 2019/1/9
   */
  @RequestMapping("iuDo")
  @ApiOperation(httpMethod = "POST", value = "菜单新增或者修改")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
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
  @NotDisplaySql
  public BackResult delete(@RequestBody MenuPoJo menuPoJo) {
    menuService.delete(menuPoJo);
    return BackResultUtils.ok();
  }
}
