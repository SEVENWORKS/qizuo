package com.qizuo.provider.interceptor;

import com.qizuo.provider.utils.JsonUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/** 日志记录 所有的访问都会进入这个 */
@Component
public class LogHandler extends AbstractHandler {
  @Override
  public WxMpXmlOutMessage handle(
      WxMpXmlMessage wxMessage,
      Map<String, Object> context,
      WxMpService wxMpService,
      WxSessionManager sessionManager) {
    this.logger.info("\n接收到请求消息，内容：{}", JsonUtils.toJson(wxMessage));
    return null;
  }
}
