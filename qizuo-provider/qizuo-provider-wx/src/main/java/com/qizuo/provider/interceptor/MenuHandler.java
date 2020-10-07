package com.qizuo.provider.interceptor;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.EventType;

/** 自定义菜单连接事件 就是自定义菜单的按钮事件触发后会到这里面来 */
@Component
public class MenuHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(
      WxMpXmlMessage wxMessage,
      Map<String, Object> context,
      WxMpService weixinService,
      WxSessionManager sessionManager) {
    String msg =
        String.format(
            "type:%s, event:%s, key:%s",
            wxMessage.getMsgType(), wxMessage.getEvent(), wxMessage.getEventKey());
    if (EventType.VIEW.equals(wxMessage.getEvent())) {
      return null;
    }

    return WxMpXmlOutMessage.TEXT()
        .content(msg)
        .fromUser(wxMessage.getToUser())
        .toUser(wxMessage.getFromUser())
        .build();
  }
}
