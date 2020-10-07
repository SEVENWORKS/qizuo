package com.qizuo.provider.interceptor;

import com.qizuo.provider.config.builder.TextBuilder;
import com.qizuo.provider.controller.admin.WxMsgKVController;
import com.qizuo.util.parse.JacksonUtil;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/** */
@Component
public class MsgHandler extends AbstractHandler {
  // redis操作对象
  @Resource private RedisTemplate<String, Object> redisTemplate;

  @Override
  public WxMpXmlOutMessage handle(
      WxMpXmlMessage wxMessage,
      Map<String, Object> context,
      WxMpService weixinService,
      WxSessionManager sessionManager) {

    // 消息处理
    if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
      // TODO 可以选择将消息保存到本地
    }

    // 转客服处理 需要认证才能开通
    //    try {
    //      if (StringUtils.startsWithAny(wxMessage.getContent(), "七作")
    //          && weixinService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0) {
    //        return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
    //            .fromUser(wxMessage.getToUser())
    //            .toUser(wxMessage.getFromUser())
    //            .build();
    //      }
    //    } catch (WxErrorException e) {
    //      e.printStackTrace();
    //    }

    // TODO 组装回复消息
    // redis中储备消息回复
    List<Object> list =
        redisTemplate
            .opsForList()
            .range(
                WxMsgKVController.key,
                0,
                redisTemplate.opsForList().size(WxMsgKVController.key) - 1);
    Map<String, String> mapBig = new HashMap<>();
    list.forEach(
        (key) -> {
          try {
            mapBig.putAll(JacksonUtil.parseJson((String) key, Map.class));
          } catch (IOException e) {
          }
        });
    // 存在消息则用，否则默认的
    String content =
        StringUtils.isBlank(mapBig.get(wxMessage.getContent()))
            ? "生活不能给你的甜，获取我可以——请享受无法回避的痛苦"
            : mapBig.get(wxMessage.getContent()); // JsonUtils.toJson(wxMessage);
    return new TextBuilder().build(content, wxMessage, weixinService);
  }
}
