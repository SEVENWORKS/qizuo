/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.rpc;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.provider.service.UserFeignApi;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * rpc调用接口.
 */
@RefreshScope
@RestController
@Api(value = "API-UserFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserFeignClient extends BaseController implements UserFeignApi {
    @Override
    public BackResult<List<String>> queryMessageKeyList(List<String> messageKeyList) {
        return null;
    }
//	@Resource
//	private OmcOrderService omcOrderService;
//
//	@Override
//	@ApiOperation(httpMethod = "POST", value = "根据订单号查询订单信息")
//	public Wrapper<OrderDto> queryByOrderNo(@PathVariable("orderNo") String orderNo) {
//		logger.info("selectByOrderNo - 根据订单号查询订单信息. orderNo={}", orderNo);
//		OrderDto orderDto = omcOrderService.queryOrderDtoByOrderNo(orderNo);
//		return WrapMapper.ok(orderDto);
//	}
}
