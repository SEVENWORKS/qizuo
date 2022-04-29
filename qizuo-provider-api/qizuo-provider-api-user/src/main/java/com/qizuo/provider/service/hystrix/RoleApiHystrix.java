/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.RolePoJo;
import com.qizuo.provider.service.RoleFeignApi;
import org.springframework.stereotype.Component;

/** role hystrix. */
@Component
public class RoleApiHystrix implements RoleFeignApi {

  @Override
  public BackResult list(final RolePoJo rolePoJo) {
    return BackResultUtils.error();
  }

  @Override
  public BackResult query(final RolePoJo rolePoJo) {
    return BackResultUtils.error();
  }
}
