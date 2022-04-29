package com.qizuo.zuul.controller.admin;

import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.model.service.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 这个主要是授权码模式和简化模式使用
 * 自定义授权码登录界面
 * 1.loginProcessUrl中的URL必须和WebSecurityConfigurerAdapter中配置的一样，就是页面commit的url必须和其保持一致,但要注意如果项目有前缀，只需要在两个相同的前提下，在commit页面加即可
 * 2.这个地方需要将鉴权路径放开，包括security、token、zuul
 * 3.这个地方模板用的是thymeleaf，必须添加依赖和application中配置
 * 4.必须配置在WebSecurityConfigurerAdapter配置登录页和登录commiturl
 */
@Controller
@Api(value = "User-OauthLoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthLoginController  extends BaseController {
    //入口地址
    @Value("${port_url}")
    String port_url;

    @GetMapping("/qizuo/login")
    @ApiOperation(httpMethod = "GET", value = "授权码登录界面")
    @NoNeedAccessAuthentication
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/port/qizuo/authorize");//
        return "login";
    }
}
