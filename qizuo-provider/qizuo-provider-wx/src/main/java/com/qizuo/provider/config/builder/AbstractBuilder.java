package com.qizuo.provider.config.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 这个builder主要是为了回复文本消息的构建的 这个构建就是构建成一个xml形式的文体 */
public abstract class AbstractBuilder {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract WxMpXmlOutMessage build(
      String content, WxMpXmlMessage wxMessage, WxMpService service);
}
