/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UacMqMessageApiHystrix.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.qizuo.provider.service.hystrix;


import com.qizuo.base.model.result.BackResult;
import com.qizuo.provider.service.UserFeignApi;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * user hystrix.
 */
@Component
public class UserApiHystrix implements UserFeignApi {
	@Override
	public BackResult<List<String>> queryMessageKeyList(final List<String> messageKeyList) {
		return null;
	}
}
