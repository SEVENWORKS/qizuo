/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.provider.model.po.MsgPoJo;
import com.qizuo.provider.service.MsgFeignApi;
import org.springframework.stereotype.Component;

/** role hystrix. */
@Component
public class MsgApiHystrix implements MsgFeignApi {

  @Override
  public BackResult list(final MsgPoJo msgPoJo) {
    return null;
  }

  @Override
  public BackResult query(final MsgPoJo msgPoJo) {
    return null;
  }
}
