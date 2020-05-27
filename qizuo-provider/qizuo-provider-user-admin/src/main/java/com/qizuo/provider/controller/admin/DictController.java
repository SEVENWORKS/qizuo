package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.base.DictPoJo;
import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fangl
 * @description: 字典表控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "${url_module}/dict/", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
@RestController
@Api(value = "UserAdmin-DictController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * @author: fangl
     * @description: 字典表列表
     * @date: 17:10 2019/1/8
     */
    @RequestMapping("page")
    @ApiOperation(httpMethod = "POST", value = "字典表列表")
    @LogAnnotation
    @ValidateRequestAnnotation
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult page(PageDto<DictPoJo> poJos, DictPoJo dictPoJo) {
        poJos.setEntity(dictPoJo);
        return BackResultUtils.ok(dictService.qPageQZ(poJos));
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
    public BackResult query(DictPoJo dictPoJo) {
        return BackResultUtils.ok(dictService.query(dictPoJo));
    }

    /**
     * @author: fangl
     * @description: 字典表新增或者修改
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("iuDo")
    @ApiOperation(httpMethod = "POST", value = "字典表新增或者修改")
    @LogAnnotation
    @ValidateRequestAnnotation
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult iuDo(DictPoJo dictPoJo) {
        //插入或者更新
        dictService.iuBatch(dictPoJo);
        return BackResultUtils.ok();
    }

    /**
     * @author: fangl
     * @description: 字典表删除
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("delete")
    @ApiOperation(httpMethod = "POST", value = "字典表删除")
    @LogAnnotation
    @ValidateRequestAnnotation
    @NotDisplaySql
    @NoNeedAccessAuthentication
    public BackResult delete(DictPoJo dictPoJo) {
        dictService.uStatus(dictPoJo);
        return BackResultUtils.ok();
    }
}
