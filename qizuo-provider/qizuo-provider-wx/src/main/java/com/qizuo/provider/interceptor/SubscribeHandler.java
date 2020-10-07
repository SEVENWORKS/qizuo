package com.qizuo.provider.interceptor;

import com.qizuo.provider.config.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/** 用户关注的时候进入 */
@Component
public class SubscribeHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(
      WxMpXmlMessage wxMessage,
      Map<String, Object> context,
      WxMpService weixinService,
      WxSessionManager sessionManager)
      throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    // 获取微信用户基本信息 个人的订阅号是获取不到用户信息的
    //    try {
    //      WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(),
    // null);
    //      if (userWxInfo != null) {
    //        // TODO 可以添加关注用户到本地数据库
    //      }
    //    } catch (WxErrorException e) {
    //      if (e.getError().getErrorCode() == 48001) {
    //        this.logger.info("该公众号没有获取用户信息权限！");
    //      }
    //    }

    // 判断关注的方式，是否要进行特殊处理
    WxMpXmlOutMessage responseResult = null;
    try {
      responseResult = this.handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }
    if (responseResult != null) {
      return responseResult;
    }

    try {
      return new TextBuilder().build("生活不止眼前的苟且，还有读不懂的诗和到不了的远方。", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  /** 处理特殊请求，比如如果是扫码进来的，可以做相应处理 */
  private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
    // TODO
    return null;
  }
}
