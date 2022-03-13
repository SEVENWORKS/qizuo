package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** 核心微信入口业务接口 http://qizuo.free.idcfengye.com/wx/wx8d37841ba6e03714/qizuo */
@Slf4j
@AllArgsConstructor
// 这个是controller+requestbody 默认情况下返回string是会走视图模板解析器的
// 如果加上了requestbody就不会走了，但是这时候如果加上json的解析器如webmvconfig，这时候如果单独返回一个string对象，是会报错的。
@RestController
@RequestMapping("/{appid}/qizuo")
@Api(value = "Wx-PortalController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WxPortalController {
  // 核心业务service
  private final WxMpService wxService;
  // 消息处理的router
  private final WxMpMessageRouter messageRouter;

  /**
   * 这个方法主要是为了开启微信入口配置的验证的接口
   *
   * @param appid
   * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
   * @param timestamp 时间戳
   * @param nonce 随机数
   * @param echostr 随机字符串
   * @return
   */
  @GetMapping(produces = "text/plain;charset=utf-8")
  @ApiOperation(httpMethod = "GET", value = "这个方法主要是为了开启微信入口配置的验证的接口")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public String authGet(
      @PathVariable String appid,
      @RequestParam(name = "signature", required = false) String signature,
      @RequestParam(name = "timestamp", required = false) String timestamp,
      @RequestParam(name = "nonce", required = false) String nonce,
      @RequestParam(name = "echostr", required = false) String echostr) {

    log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
    // 参数判断
    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
      throw new IllegalArgumentException("请求参数非法，请核实!");
    }
    // appid判断
    if (!this.wxService.switchover(appid)) {
      throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
    }
    // 微信参数解析后判断，如果正确就返回对应echostr，如果不正确就说明参数有误
    if (wxService.checkSignature(timestamp, nonce, signature)) {
      return echostr;
    }

    return "非法请求";
  }

  /**
   * 接口微信的消息 同时支持三种模式(明文密文等) 微信用户所有的互动都是进入这个方法，比如关注/消息发送等
   *
   * @param appid
   * @param requestBody 消息体
   * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
   * @param timestamp 时间戳
   * @param nonce 随机数
   * @param openid 每个用户对每个公众号有一个唯一的OpenID。
   * @param encType 加密类型
   * @param msgSignature 消息签名
   * @return
   */
  @PostMapping(produces = "application/xml; charset=UTF-8")
  @ApiOperation(httpMethod = "POST", value = "接口微信的消息")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public String post(
      @PathVariable String appid,
      @RequestBody String requestBody,
      @RequestParam("signature") String signature,
      @RequestParam("timestamp") String timestamp,
      @RequestParam("nonce") String nonce,
      @RequestParam("openid") String openid,
      @RequestParam(name = "encrypt_type", required = false) String encType,
      @RequestParam(name = "msg_signature", required = false) String msgSignature) {
    log.info(
        "\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
            + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
        openid,
        signature,
        encType,
        msgSignature,
        timestamp,
        nonce,
        requestBody);
    // 判断appid
    if (!this.wxService.switchover(appid)) {
      throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
    }
    // 判断参数是否解析正确，这个和上面get方法判断一样
    if (!wxService.checkSignature(timestamp, nonce, signature)) {
      throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
    }
    // 对消息进行处理
    String out = null;
    if (encType == null) {
      // 明文传输的消息
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
      WxMpXmlOutMessage outMessage = this.route(inMessage);
      if (outMessage == null) {
        return "";
      }

      out = outMessage.toXml();
    } else if ("aes".equalsIgnoreCase(encType)) {
      // 密文有些问题，就是会超出jdk的密文长度  这个后面有兴趣看下 TODO
      // aes加密的消息
      WxMpXmlMessage inMessage =
          WxMpXmlMessage.fromEncryptedXml(
              requestBody, wxService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
      log.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
      WxMpXmlOutMessage outMessage = this.route(inMessage);
      if (outMessage == null) {
        return "";
      }

      out = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
    }

    log.debug("\n组装回复信息：{}", out);
    return out;
  }

  // 消息路由  对不同的消息  放到不同的handler中进行解析并返回相应的响应
  private WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.messageRouter.route(message);
    } catch (Exception e) {
      log.error("路由消息时出现异常！", e);
    }

    return null;
  }
}
