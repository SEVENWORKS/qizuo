/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.service.hystrix;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.provider.model.po.FileLogPoJo;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.UploadApi;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** upload hystrix. */
@Component
public class UploadApiHystrix implements UploadApi {
  @Override
  public BackResult singleUpload(MultipartFile file) {
    return null;
  }

  @Override
  public BackResult multiUpload(MultipartFile[] multipartFiles) {
    return null;
  }

  @Override
  public BackResult downFile(HttpServletResponse response, Model model, String resourceName) {
    return null;
  }

  @Override
  public BackResult fileDelete(String resourceName) {
    return null;
  }
}
