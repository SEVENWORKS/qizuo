/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.provider.service.UserAdminFeignApi;
import org.springframework.stereotype.Component;

/** user hystrix. */
@Component
public class UserAdminApiHystrix implements UserAdminFeignApi {}
