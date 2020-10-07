package com.qizuo.provider.config;

import com.qizuo.provider.interceptor.*;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.stream.Collectors;

import static me.chanjar.weixin.common.api.WxConsts.EventType;
import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.*;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.POI_CHECK_NOTIFY;

/**
 * wechat mp configuration核心类 初始化wxmpservice 所有的handler和properties都是在这个地方处理初始化的 <br>
 * 总共四个步骤 <br>
 * 1.核心初始化类，初始化基本信息，就是公众号相关的一些，有条件的可以加上redis存储一类的 <br>
 * 2.定义相关文本解析器，为了用户发送消息进行解析 <br>
 * 3.定义相关文本构造器，为了响应用户返回的消息构造 <br>
 * 4.定义controller控制中心，主要包含两个部分，一个是菜单创造/删除/查询，二是和用户互动controller
 */
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
  /** 下面是核心初始化 */
  private final WxMpProperties properties;

  @Bean
  public WxMpService wxMpService() {
    final List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
    if (configs == null) {
      throw new RuntimeException("配置有问题！");
    }

    WxMpService service = new WxMpServiceImpl();
    service.setMultiConfigStorages(
        configs
            .stream()
            .map(
                a -> {
                  WxMpDefaultConfigImpl configStorage;
                  if (this.properties.isUseRedis()) {
                    // redis存储消息
                    final WxMpProperties.RedisConfig redisConfig = this.properties.getRedisConfig();
                    JedisPool jedisPool =
                        new JedisPool(redisConfig.getHost(), redisConfig.getPort());
                    configStorage = null; // new WxMpRedisConfigImpl(new JedisWxRedisOps(jedisPool),
                    // a.getAppId());
                  } else {
                    // 默认文件存储消息
                    configStorage = new WxMpDefaultConfigImpl();
                  }

                  configStorage.setAppId(a.getAppId());
                  configStorage.setSecret(a.getSecret());
                  configStorage.setToken(a.getToken());
                  configStorage.setAesKey(a.getAesKey());
                  return configStorage;
                })
            .collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));
    return service;
  }

  /** 下面路由是用户发送消息过来的解析 */
  private final LogHandler logHandler;

  private final MenuNullHandler menuNullHandler;
  private final KfSessionHandler kfSessionHandler;
  private final StoreCheckNotifyHandler storeCheckNotifyHandler;
  private final LocationHandler locationHandler;
  private final MenuHandler menuHandler;
  private final MsgHandler msgHandler;
  private final UnsubscribeHandler unsubscribeHandler;
  private final SubscribeHandler subscribeHandler;
  private final ScanHandler scanHandler;

  @Bean
  public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
    final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

    // 记录所有事件的日志 （异步执行）
    newRouter.rule().handler(this.logHandler).next();

    // 接收客服会话管理事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(KF_CREATE_SESSION)
        .handler(this.kfSessionHandler)
        .end();
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(KF_CLOSE_SESSION)
        .handler(this.kfSessionHandler)
        .end();
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(KF_SWITCH_SESSION)
        .handler(this.kfSessionHandler)
        .end();

    // 门店审核事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(POI_CHECK_NOTIFY)
        .handler(this.storeCheckNotifyHandler)
        .end();

    // 自定义菜单事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(EventType.CLICK)
        .handler(this.menuHandler)
        .end();

    // 点击菜单连接事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(EventType.VIEW)
        .handler(this.menuNullHandler)
        .end();

    // 关注事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(SUBSCRIBE)
        .handler(this.subscribeHandler)
        .end();

    // 取消关注事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(UNSUBSCRIBE)
        .handler(this.unsubscribeHandler)
        .end();

    // 上报地理位置事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(EventType.LOCATION)
        .handler(this.locationHandler)
        .end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(XmlMsgType.LOCATION).handler(this.locationHandler).end();

    // 扫码事件
    newRouter
        .rule()
        .async(false)
        .msgType(EVENT)
        .event(EventType.SCAN)
        .handler(this.scanHandler)
        .end();

    // 默认
    newRouter.rule().async(false).handler(this.msgHandler).end();

    return newRouter;
  }
}
