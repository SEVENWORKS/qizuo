/*
 * Copyright (c) 2020.
 * author：qizuo
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
