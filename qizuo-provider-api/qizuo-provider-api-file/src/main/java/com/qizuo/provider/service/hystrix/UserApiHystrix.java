/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.provider.model.po.UserPoJo;
import com.qizuo.provider.service.UserFeignApi;
import org.springframework.stereotype.Component;

/** user hystrix. */
@Component
public class UserApiHystrix implements UserFeignApi {
  @Override
  public BackResult list(final UserPoJo userPoJo) {
    return null;
  }

  @Override
  public BackResult page(final PageDto<UserPoJo> poJos) {
    return null;
  }

  @Override
  public BackResult query(final UserPoJo userPoJo) {
    return null;
  }
}
