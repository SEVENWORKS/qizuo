/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.exception;

import com.qizuo.base.model.base.LogDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.config.properties.baseProperties.LogTypeEnum;
import com.qizuo.util.http.HttpUtil;
import com.qizuo.util.http.MobileUtil;
import com.qizuo.util.http.RequestUtil;
import com.qizuo.util.parse.JacksonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: fangl
 * @Description: 异常触发拦截器(属于springmvc)
 * @Date: 10:12 2018/11/19
 */
//所有@controller绑定的，都会在此对此拦截处理，相当于controller拦截器,包括@InitBinder/@ModelAttribute/@ExceptionHandler
//和@RestControllerAdvice区别相当于，@controller和@restController
@ControllerAdvice
public class SpringmvcException {
    /**
     * @Author: fangl
     * @Description: 默认异常处理方法(这个地方的处理主要是web端的拦截, 移动端请求请自行catch)
     * @Date: 0:04 2018/11/25
     */
    @ResponseBody
    //表明返回后的状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //执行过后，所有@controller的方法的异常都会进入这个方法中
    @ExceptionHandler(value = {Exception.class})
    public BackResult defaultHandler(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        //是否打印异常
        //e.printStackTrace();

        //是否保存异常
        LogDto logDto = new LogDto();
        logDto.setType(LogTypeEnum.OPERATION_LOG);
        //转换其它信息
        String userAgent = MobileUtil.getUserAgent(request).toString();
        String requestParam = HttpUtil.getParamsChar(request);
        String requestBody = HttpUtil.getRequestBodyChar(request);
        String requestMethod = HttpUtil.getRequestMethod(request);
        logDto.setContent(getStackTraceAsString(e) + "\n"
                + "*************************************" + "\n"
                + "userAgent:" + userAgent + "\n"
                + "requestParam:" + requestParam + "\n"
                + "requestBody:" + requestBody + "\n"//这个requestbody和requestparam是对立的，一般只会保存一个
                + "requestMethod:" + requestMethod + "\n");

        //判断是否是ajax请求(省略)
        //ajax请求直接返回json串
        /*try {
            RequestUtil.httpBackJson(response, JacksonUtil.toJson(new BackResult<>(BackResult.ERROR_CODE, BackResult.ERROR_MESSAGE)));
        } catch (IOException ee) {
        }*/
        return new BackResult<>(BackResult.ERROR_CODE, BackResult.ERROR_MESSAGE);
    }

    /**
     * @author: fangl
     * @description: 将ErrorStack转化为String
     * @date: 14:44 2019/1/11
     */
    public static String getStackTraceAsString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
