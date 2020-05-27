package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fangl
 * @description: 菜单控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "${url_module}/menu/", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
@RestController
@Api(value = "User-MenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * @author: fangl
     * @description: 获取相应菜单列表
     * @date: 17:10 2019/1/8
     */
    @PostMapping("list")
    @ApiOperation(httpMethod = "POST", value = "获取相应菜单列表")
    @LogAnnotation
    @ValidateRequestAnnotation
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult list(MenuPoJo menuPoJo) {
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
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult qEachList(MenuPoJo menuPoJo) {
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
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult page(PageDto<MenuPoJo> pagePoJo, MenuPoJo menuPoJo) {
        pagePoJo.setEntity(menuPoJo);
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
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult query(MenuPoJo menuPoJo) {
        return BackResultUtils.ok(menuService.query(menuPoJo));
    }
}