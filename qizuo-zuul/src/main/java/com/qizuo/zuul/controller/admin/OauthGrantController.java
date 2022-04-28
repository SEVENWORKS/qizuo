package com.qizuo.zuul.controller.admin;

import com.qizuo.base.annotation.NoNeedAccessAuthentication;
import com.qizuo.base.model.service.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 这个主要是授权码模式和简化模式使用
 * 自定义授权码授权页面
 * 1.@Session这个必须有，相当于方法的覆盖
 * 2.requestMapping这个必须是这个url(要不然要配置别的麻烦)
 */
@Controller
@SessionAttributes("authorizationRequest")
@Api(value = "User-OauthGrantController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthGrantController extends BaseController {
    @RequestMapping("/oauth/confirm_access")
    @ApiOperation(httpMethod = "GET", value = "授权码授权界面")
    @NoNeedAccessAuthentication
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("grant");
        view.addObject("clientId", authorizationRequest.getClientId());
        view.addObject("scopes", authorizationRequest.getScope());
        return view;
    }
}
