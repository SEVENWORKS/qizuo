package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** 菜单接口 */
@AllArgsConstructor
@RestController
@RequestMapping("/menu/{appid}")
@Api(value = "Wx-MenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WxMenuController {
  private final WxMpService wxService;

  /**
   *
   *
   * <pre>
   * 自定义菜单创建接口
   * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
   * 如果要创建个性化菜单，请设置matchrule属性
   * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
   * </pre>
   *
   * @return 如果是个性化菜单，则返回menuid，否则返回null
   */
  @PostMapping("/create")
  @ApiOperation(httpMethod = "POST", value = "自定义菜单创建接口")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult menuCreate(@PathVariable String appid, @RequestBody WxMenu menu) {
    String str = null;
    try {
      str = this.wxService.switchoverTo(appid).getMenuService().menuCreate(menu);
    } catch (Exception e) {
      return BackResultUtils.error();
    }
    return BackResultUtils.ok(str);
  }

  /**
   *
   *
   * <pre>
   * 自定义菜单创建接口
   * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
   * 如果要创建个性化菜单，请设置matchrule属性
   * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
   * </pre>
   *
   * @return 如果是个性化菜单，则返回menuid，否则返回null
   */
  @PostMapping("/createByJson")
  @ApiOperation(httpMethod = "POST", value = "自定义菜单创建接口json")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult menuCreate(@PathVariable String appid, @RequestBody String json) {
    String str = null;
    try {
      str = this.wxService.switchoverTo(appid).getMenuService().menuCreate(json);
    } catch (Exception e) {
      return BackResultUtils.error();
    }
    return BackResultUtils.ok(str);
  }

  /**
   *
   *
   * <pre>
   * 自定义菜单删除接口，删除所有！！
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
   * </pre>
   */
  @GetMapping("/delete")
  @ApiOperation(httpMethod = "GET", value = "自定义菜单删除接口")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult menuDelete(@PathVariable String appid) {
    try {
      this.wxService.switchoverTo(appid).getMenuService().menuDelete();
    } catch (Exception e) {
      return BackResultUtils.error();
    }
    return BackResultUtils.ok();
  }

  /**
   *
   *
   * <pre>
   * 删除个性化菜单接口  更具菜单id删除单个menu
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
   * </pre>
   *
   * @param menuId 个性化菜单的menuid
   */
  @GetMapping("/delete/{menuId}")
  @ApiOperation(httpMethod = "GET", value = "删除个性化菜单接口")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult menuDelete(@PathVariable String appid, @PathVariable String menuId) {
    try {
      this.wxService.switchoverTo(appid).getMenuService().menuDelete(menuId);
    } catch (Exception e) {
      return BackResultUtils.error();
    }
    return BackResultUtils.ok();
  }

  /**
   *
   *
   * <pre>
   * 自定义菜单查询接口 这个接口可以查询自定义菜单和个性化菜单  分别由menu字段和conditionalMenu字段返回
   * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
   * </pre>
   */
  @GetMapping("/get")
  @ApiOperation(httpMethod = "GET", value = "自定义菜单查询接口")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult menuGet(@PathVariable String appid) {
    WxMpMenu wxMpMenu = null;
    try {
      wxMpMenu = this.wxService.switchoverTo(appid).getMenuService().menuGet();
    } catch (Exception e) {
      BackResultUtils.error();
    }
    return BackResultUtils.ok(wxMpMenu);
  }

  /**
   * 和上面查询接口区别是：这个接口只能查询到自定义菜单的信息，无法查询到个性化菜单的信息
   *
   * <pre>
   * 获取自定义菜单配置接口
   * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
   * 请注意：
   * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
   * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
   * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
   * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
   * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
   *  接口调用请求说明:
   * http请求方式: GET（请使用https协议）
   * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
   * </pre>
   */
  @GetMapping("/getSelfMenuInfo")
  @ApiOperation(httpMethod = "GET", value = "获取自定义菜单配置接口")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult getSelfMenuInfo(@PathVariable String appid) {
    WxMpGetSelfMenuInfoResult wxMpGetSelfMenuInfoResult = null;
    try {
      wxMpGetSelfMenuInfoResult =
          this.wxService.switchoverTo(appid).getMenuService().getSelfMenuInfo();
    } catch (Exception e) {
      return BackResultUtils.error();
    }
    return BackResultUtils.ok(wxMpGetSelfMenuInfoResult);
  }

  //    @GetMapping("/create")
  //    public String menuCreateSample(@PathVariable String appid) throws WxErrorException,
  // MalformedURLException {
  //        WxMenu menu = new WxMenu();
  //          WxMenuButton button1 = new WxMenuButton();
  //        button1.setType(MenuButtonType.CLICK);
  //        button1.setName("今日歌曲");
  //        button1.setKey("V1001_TODAY_MUSIC");
  //
  ////        WxMenuButton button2 = new WxMenuButton();
  ////        button2.setType(WxConsts.BUTTON_MINIPROGRAM);
  ////        button2.setName("小程序");
  ////        button2.setAppId("wx286b93c14bbf93aa");
  ////        button2.setPagePath("pages/lunar/index.html");
  ////        button2.setUrl("http://mp.weixin.qq.com");
  //
  //        WxMenuButton button3 = new WxMenuButton();
  //        button3.setName("菜单");
  //
  //        menu.getButtons().add(button1);
  ////        menu.getButtons().add(button2);
  //        menu.getButtons().add(button3);
  //
  //        WxMenuButton button31 = new WxMenuButton();
  //        button31.setType(MenuButtonType.VIEW);
  //        button31.setName("搜索");
  //        button31.setUrl("http://www.soso.com/");
  //
  //        WxMenuButton button32 = new WxMenuButton();
  //        button32.setType(MenuButtonType.VIEW);
  //        button32.setName("视频");
  //        button32.setUrl("http://v.qq.com/");
  //
  //        WxMenuButton button33 = new WxMenuButton();
  //        button33.setType(MenuButtonType.CLICK);
  //        button33.setName("赞一下我们");
  //        button33.setKey("V1001_GOOD");
  //
  //        WxMenuButton button34 = new WxMenuButton();
  //        button34.setType(MenuButtonType.VIEW);
  //        button34.setName("获取用户信息");
  //
  //        ServletRequestAttributes servletRequestAttributes =
  //            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
  //        if (servletRequestAttributes != null) {
  //            HttpServletRequest request = servletRequestAttributes.getRequest();
  //            URL requestURL = new URL(request.getRequestURL().toString());
  //            String url =
  // this.wxService.switchoverTo(appid).getOAuth2Service().buildAuthorizationUrl(
  //                String.format("%s://%s/wx/redirect/%s/greet", requestURL.getProtocol(),
  // requestURL.getHost(), appid),
  //                WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
  //            button34.setUrl(url);
  //        }
  //
  //        button3.getSubButtons().add(button31);
  //        button3.getSubButtons().add(button32);
  //        button3.getSubButtons().add(button33);
  //        button3.getSubButtons().add(button34);
  //
  //        this.wxService.switchover(appid);
  //        return this.wxService.getMenuService().menuCreate(menu);
  //    }

  //  /**
  //   *
  //   *
  //   * <pre>
  //   * 测试个性化菜单匹配结果 就是同一个appid下不同的微信用户看到的自定义菜单是不一样的，这个接口仅供测试
  //   * 详情请见: http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html
  //   * </pre>
  //   *
  //   * @param userid 可以是粉丝的OpenID，也可以是粉丝的微信号。
  //   */
  //  @GetMapping("/menuTryMatch/{userid}")
  //  public WxMenu menuTryMatch(@PathVariable String appid, @PathVariable String userid)
  //      throws WxErrorException {
  //    return this.wxService.switchoverTo(appid).getMenuService().menuTryMatch(userid);
  //  }
}
