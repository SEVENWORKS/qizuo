/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UacMqMessageFeignApi.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.qizuo.provider.service;


import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.otherProperties.feign.OAuth2FeignAutoConfiguration;
import com.qizuo.provider.service.hystrix.UserApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * user feign api.
 */
@FeignClient(value = "qizuo-provider-user", configuration = OAuth2FeignAutoConfiguration.class, fallback = UserApiHystrix.class)
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
