/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.base.utils.UserUtil;
import com.qizuo.provider.model.po.CommonPoJo;
import com.qizuo.provider.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 二维码解析. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/common/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
// swagger
@Api(value = "Common-Controller", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommonController extends BaseController {
    @Autowired private CommonService commonService;
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
    @PreAuthorize("hasAuthority('ROLE_COMMON')")
    public BackResult query() {
        CommonPoJo commonPoJo=new CommonPoJo();
        commonPoJo.setBaseCreateUserId(UserUtil.qUser().getBaseId());
        return BackResultUtils.ok(commonService.query(commonPoJo));
    }

    /**
     * @author: fangl
     * @description: 新增或者修改
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("iuDo")
    @ApiOperation(httpMethod = "POST", value = "新增或者修改")
    @LogAnnotation
    @ValidateRequestAnnotation
    @SqlDisplay
    @PreAuthorize("hasAuthority('ROLE_COMMON')")
    public BackResult iuDo(@RequestBody CommonPoJo commonPoJo) {
        if (StringUtils.isBlank(commonPoJo.getBaseId())) {
            // 插入
            commonService.insert(commonPoJo);
        } else {
            // 更新
            commonService.update(commonPoJo);
        }
        return BackResultUtils.ok();
    }
}
