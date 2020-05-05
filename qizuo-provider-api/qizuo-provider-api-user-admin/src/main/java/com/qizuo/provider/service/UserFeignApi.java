/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service;


import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.pluginProperties.feign.OAuth2FeignAutoConfigurationY;
import com.qizuo.provider.service.hystrix.UserApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * user feign api.
 */
@FeignClient(value = "qizuo-provider-user", configuration = OAuth2FeignAutoConfigurationY.class, fallback = UserApiHystrix.class)
public interface UserFeignApi {


	/**
	 * Query waiting confirm message list wrapper.
	 *
	 * @param messageKeyList the message key list
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/uac/message/queryMessageKeyList")
	BackResult<List<String>> queryMessageKeyList(@RequestParam("messageKeyList") List<String> messageKeyList);
}
