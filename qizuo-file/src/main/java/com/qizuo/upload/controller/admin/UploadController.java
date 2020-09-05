/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.upload.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.annotation.NotDisplaySql;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.upload.model.po.FileLogPoJo;
import com.qizuo.upload.model.po.FilePoJo;
import com.qizuo.upload.service.FileLogService;
import com.qizuo.upload.service.FileService;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/** 文件上传下载. */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
  value = "/upload/",
  method = RequestMethod.POST,
  produces = {"application/json;charset=UTF-8"}
)
@RestController
@RefreshScope
// swagger
@Api(value = "File-UploadController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadController extends BaseController {
  @Autowired private FileService fileService;
  @Autowired private FileLogService fileLogService;

  @Value("file_upload_path")
  private String fileUploadPath;
  /**
   * @author: fangl
   * @description: 单文件上传
   * @date: 15:45 2019/1/8
   */
  @PostMapping("singleUpload")
  @ApiOperation(httpMethod = "POST", value = "单文件上传")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  public BackResult singleUpload(
      MultipartFile multipartFile, FilePoJo filePoJo, FileLogPoJo fileLogPoJo) {
    if (multipartFile.isEmpty()) {
      return BackResultUtils.error("上传失败，请选择文件");
    }

    String fileName = multipartFile.getOriginalFilename();
    File dest = new File(fileUploadPath + fileName);
    try {
      multipartFile.transferTo(dest);
      // 插入
      fileService.insert(filePoJo);
      fileLogService.insert(fileLogPoJo);
      return BackResultUtils.ok();
    } catch (IOException e) {
      return BackResultUtils.error();
    }
  }

  /**
   * @author: fangl
   * @description: 多文件上传
   * @date: 15:45 2019/1/8
   */
  @PostMapping("multiUpload")
  @ApiOperation(httpMethod = "POST", value = "多文件上传")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult multiUpload(
      @RequestParam(value = "files") MultipartFile[] multipartFiles,
      List<FilePoJo> filePoJos,
      List<FileLogPoJo> fileLogPoJos) {
    if (ObjectIsEmptyUtils.isEmpty(multipartFiles)) {
      return BackResultUtils.error("上传失败，请选择文件");
    }

    // 上传
    for (int i = 0; i < multipartFiles.length; i++) {
      String fileName = multipartFiles[i].getOriginalFilename();
      File dest = new File(fileUploadPath + fileName);
      try {
        multipartFiles[i].transferTo(dest);
        // 插入
        fileService.insert(filePoJos.get(i));
        fileLogService.insert(fileLogPoJos.get(i));
      } catch (IOException e) {
      }
    }
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 文件下载
   * @date: 16:14 2019/1/9
   */
  @PostMapping("downFile")
  @ApiOperation(httpMethod = "POST", value = "文件下载")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  public BackResult downFile(HttpServletResponse response, Model model, FilePoJo filePoJo) {
    // 查询文件
    filePoJo = fileService.query(filePoJo);
    // 待下载文件名
    String fileName = filePoJo.getName();
    // 设置为png格式的文件
    response.setHeader(
        "content-type",
        filePoJo.getResourceName().substring(filePoJo.getResourceName().lastIndexOf(".") + 1));
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    byte[] buff = new byte[1024];
    // 创建缓冲输入流
    BufferedInputStream bis = null;
    OutputStream outputStream = null;

    try {
      outputStream = response.getOutputStream();
      // 这个路径为待下载文件的路径
      bis = new BufferedInputStream(new FileInputStream(new File(filePoJo.getResourceName())));
      int read = bis.read(buff);

      // 通过while循环写入到指定了的文件夹中
      while (read != -1) {
        outputStream.write(buff, 0, buff.length);
        outputStream.flush();
        read = bis.read(buff);
      }
    } catch (IOException e) {
      // 出现异常返回给页面失败的信息
      model.addAttribute(BackResultUtils.error());
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (IOException e) {
        }
      }
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e) {
        }
      }
    }
    // 成功后返回成功信息
    model.addAttribute(BackResultUtils.ok());
    return BackResultUtils.ok();
  }

  /**
   * @author: fangl
   * @description: 文件删除(多个单个)
   * @date: 16:14 2019/1/9
   */
  @PostMapping("fileDelete")
  @ApiOperation(httpMethod = "POST", value = "文件删除")
  @LogAnnotation
  @ValidateRequestAnnotation
  @NotDisplaySql
  @NoNeedAccessAuthentication
  public BackResult fileDelete(FilePoJo filePoJo, FileLogPoJo fileLogPoJo) {
    // 查询文件
    filePoJo = fileService.query(filePoJo);
    // 文件路径
    String filepath = filePoJo.getResourceName();
    File file = new File(filepath);
    if (file.exists()) { // 文件是否存在
      if (file.delete()) { // 存在就删了，返回1
        // 删除
        fileService.delete(filePoJo);
        // 状态修改
        fileLogService.uLogResult(fileLogPoJo);
        return BackResultUtils.ok();
      } else {
        return BackResultUtils.error();
      }
    } else {
      return BackResultUtils.error();
    }
  }
}
