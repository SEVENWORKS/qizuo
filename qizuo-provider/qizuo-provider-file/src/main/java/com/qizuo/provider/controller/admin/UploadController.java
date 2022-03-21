/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.model.service.BaseController;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import com.qizuo.provider.model.po.FileLogPoJo;
import com.qizuo.provider.model.po.FilePoJo;
import com.qizuo.provider.service.FileLogService;
import com.qizuo.provider.service.FileService;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import com.qizuo.util.count.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件上传下载.
 */
// produces有两个好处：一个是浏览器查看方便（json自动格式化，带搜索），另一个可以防止中文乱码。
@RequestMapping(
        value = "/upload/",
        method = {RequestMethod.POST,RequestMethod.GET},
        produces = {"application/json;charset=UTF-8"}
)
@RestController
@RefreshScope
// swagger
@Api(value = "File-UploadController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//开启前置权限判断
//开启参数认证
@Validated
public class UploadController extends BaseController {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileLogService fileLogService;
    //文件上传路径前缀
    @Value("${file_upload_path}")
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
    @SqlDisplay
    @PreAuthorize("hasAuthority('ROLE_FILE')")
    public BackResult singleUpload(MultipartFile file) {
        //文件校验
        if (file.isEmpty()) {
            return BackResultUtils.error("上传失败，请选择文件");
        }
        //大小校验,在yml中已经配置

        //上传
        String systemPath = System.getProperties().getProperty("user.dir").substring(0, 3);//上传根路径
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);//文件后缀
        String finalPath=systemPath + fileUploadPath + File.separator + "upload" + File.separator+suffix+ File.separator;//上传最终路径
        try {
            //如果目录不存在，即创建
            File pathFile=new File(finalPath);
            if (!pathFile.exists()) {
              pathFile.mkdirs();
            }
            //流写入文件，只能使用一次，后面获取stream就获取不到了
            String fileName = RandomUtil.uuid() + "_" + file.getOriginalFilename();//文件原始名称
            File dest = new File(finalPath + fileName);
            file.transferTo(dest);
            // 插入
            FilePoJo filePoJo = new FilePoJo();
            filePoJo.setResourceName(finalPath + fileName);//基础
            filePoJo.setName(file.getOriginalFilename());//基础
            fileService.insert(filePoJo);
            FileLogPoJo fileLogPoJo = new FileLogPoJo();
            fileLogPoJo.setResourceName(finalPath + fileName);//基础
            fileLogPoJo.setName(file.getOriginalFilename());//基础
            fileLogPoJo.setUploadResult(GlobalConstant.STATUS_YES);//结果
            fileLogService.insert(fileLogPoJo);
            return BackResultUtils.wrap(ResultCodeEnum.GLSUCCESS.code(),ResultCodeEnum.GLSUCCESS.msg(),filePoJo.getBaseId());
        } catch (IOException e) {
            return BackResultUtils.error();
        }
    }

    /**
     * @author: fangl
     * @description: 文件下载
     * @date: 16:14 2019/1/9
     */
    @RequestMapping("downFile")
    @ApiOperation(httpMethod = "POST", value = "文件下载")
    @LogAnnotation
    @ValidateRequestAnnotation
    @SqlDisplay
    @PreAuthorize("hasAuthority('ROLE_FILE')")
    public BackResult downFile(HttpServletResponse response, Model model, String baseId) {
        FilePoJo filePoJo = new FilePoJo();
        filePoJo.setBaseId(baseId);
        // 查询文件
        filePoJo = fileService.query(filePoJo);
        //操作对象
        byte[] buff = new byte[1024];
        // 创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            // 设置下载文件
            String fileName = filePoJo.getName();
            response.setHeader(
                    "content-type",
                    filePoJo.getResourceName().substring(filePoJo.getResourceName().lastIndexOf(".") + 1));
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName,"UTF-8"));
            // 这个路径为待下载文件的路径
            outputStream = response.getOutputStream();
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
    @SqlDisplay
    @PreAuthorize("hasAuthority('ROLE_FILE')")
    public BackResult fileDelete(String baseId) {
        // 查询文件
        FilePoJo filePoJo = new FilePoJo();
        filePoJo.setBaseId(baseId);
        filePoJo = fileService.query(filePoJo);
        //日志
        FileLogPoJo fileLogPoJo = new FileLogPoJo();
        fileLogPoJo.setUploadResult(GlobalConstant.STATUS_NO);
        fileLogPoJo.setResourceName(filePoJo.getResourceName());
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
