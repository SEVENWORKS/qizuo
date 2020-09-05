/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.MenuPoJo;
import com.qizuo.provider.service.MenuFeignApi;
import com.qizuo.provider.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/** rpc调用接口. */
@RestController
@Api(value = "API-MenuFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuFeignClient extends BaseController implements MenuFeignApi {
  @Autowired private MenuService menuService;

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询菜单列表")
  public BackResult list(MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.qList(menuPoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询菜单分页列表")
  public BackResult page(PageDto<MenuPoJo> pagePoJo) {
    return BackResultUtils.ok(menuService.qPageQZ(pagePoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询菜单树状")
  public BackResult qEachList(MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.qEachList(menuPoJo));
  }

  @Override
  @ApiOperation(httpMethod = "POST", value = "查询菜单单个")
  public BackResult query(MenuPoJo menuPoJo) {
    return BackResultUtils.ok(menuService.query(menuPoJo));
  }
}
