/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.controller.error;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: fangl @Description:
 * 实际上当springboot项目出现异常时，默认会跳转到/error，而/error则是由BasicErrorController进行处理，这里重写errorcode，统一采用resultcode处理,基本上针对404和500错误 @Date:
 * 10:12 2018/11/19
 */
@RestController
public class BaseErrorController implements ErrorController {
  // 注意这个地方不能改，要不然errorcontroller不会生效
  private static final String ERROR_PATH = "/error";

  @Autowired private ErrorAttributes errorAttributes;

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }

  /** 除web页面外的错误处理，比如json/xml等,produces = {MediaType.APPLICATION_JSON_VALUE} */
  @RequestMapping(value = ERROR_PATH)
  @ResponseBody
  public BackResult errorApiHander(HttpServletRequest request) {
    ServletWebRequest requestAttributes = new ServletWebRequest(request);
    Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
    return BackResultUtils.error();
  }

  /** web页面错误处理 */
  //    @RequestMapping(value=ERROR_PATH,produces="text/html")
  //    @ResponseBody
  //    public BackResult errorPageHandler(HttpServletRequest request,HttpServletResponse response)
  // {
  //        ServletWebRequest requestAttributes = new ServletWebRequest(request);
  //        Map<String, Object> attr=this.errorAttributes.getErrorAttributes(requestAttributes,
  // false);
  //        return BackResultUtils.error((String)attr.get("message"));
  //    }
}
