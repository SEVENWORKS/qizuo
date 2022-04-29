/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.provider.model.po.MenuPoJo;
import com.qizuo.provider.service.MenuFeignApi;
import org.springframework.stereotype.Component;

/** menu hystrix. */
@Component
public class MenuApiHystrix implements MenuFeignApi {
  @Override
  // final 表示给我传过来的参数，机器里面就无法改了
  public BackResult list(final MenuPoJo menuPoJo) {
    return BackResultUtils.error();
  }

  @Override
  public BackResult page(final PageDto<MenuPoJo> pagePoJo) {
    return BackResultUtils.error();
  }

  @Override
  public BackResult qEachList(final MenuPoJo menuPoJo) {
    return BackResultUtils.error();
  }

  @Override
  public BackResult query(final MenuPoJo menuPoJo) {
    return BackResultUtils.error();
  }
}
