/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.base.model.page.PageDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.FileApi;
import org.springframework.stereotype.Component;

/** file hystrix. */
@Component
public class FileApiHystrix implements FileApi {

  @Override
  public BackResult list(FilePoJo filePoJo) {
    return null;
  }

  @Override
  public BackResult page(PageDto<FilePoJo> poJos) {
    return null;
  }

  @Override
  public BackResult query(FilePoJo filePoJo) {
    return null;
  }
}
