package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** jsapi 演示接口的 controller. */
@AllArgsConstructor
@RestController
@RequestMapping("/jsapi/{appid}/qizuo")
@Api(value = "Wx-JsapiController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WxJsapiController {
  private final WxMpService wxService;

  @GetMapping("/getJsapiTicket")
  @ApiOperation(httpMethod = "GET", value = "getJsapiTicket")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public String getJsapiTicket(@PathVariable String appid) throws WxErrorException {
    final WxJsapiSignature jsapiSignature =
        this.wxService.switchoverTo(appid).createJsapiSignature("111");
    System.out.println(jsapiSignature);
    return this.wxService.getJsapiTicket(true);
  }
}
