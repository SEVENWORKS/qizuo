/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.zuul.authorizationServer;

import com.qizuo.zuul.authorizationServer.service.RestClientDetailsService;
import com.qizuo.zuul.authorizationServer.service.SecurityUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器的配置核心，统筹整个服务配置，包括token存储，认证管理
 *
 * <p>请求优先由@EnableAuthorizationServer、@EnableResourceServer 处理，剩下的无法匹配的由SpringSecurity处理。
 *
 * 以下权限控制好后，主要通过token验证一下几个：
 * 1.通过token本验证
 * 2.通过解析出来resourceId进行验证，只有一致的才能访问对应的服务器，如果不配置，可以访问所有(clientservice进行配置,resource中进行验证)
 * 3.通过解析出来authorities进行验证，只有角色一致的才能访问对应的类或者方法，如果不配置，可以访问所有(clientservice进行配置)
 * 4.通过解析出来scope进行验证，只有读写权限一致的才能访问对应的类和方法，如果不配置，可以访问所有(clientservice进行配置)
 * @PreAuthorize("hasAuthority('admin')")、@PreAuthorize("hasRole('admin')")
 * @PreAuthorize("#oauth2.hasScope('read')")
 * 这个注解可以用到类或者方法上都行，但是注解是不能重复的，并且方法会覆盖类上的配置，即如果配置两个，后面会覆盖前面的
 * 上述注解需要@EnableGlobalMethodSecurity(prePostEnabled = true)开启，这个要放到开启的类上面(如果是admin角色验证，可以不需要，因为resource上开启了，但是最好还是统一加上)
 * 注意上传鉴权可以转变全局形式，即写到资源服务器配置中(resource httpsecurity)
 * 注意@EnableGlobalMethodSecurity(prePostEnabled = true)放到认证服务controller会导致开启认证服务token失败等
 *
 *五种模式请求(本质都是获取token；下面请求的参数都要和数据库oauth_client_details配置的一致)
 * 1.客户端(client_credentials)
 * http://127.0.0.1:9300/port/user/oauth/token?grant_type=client_credentials&client_id=qizuo&client_secret=qizuo
 * 支持get和post请求；参数不要放到header中；不支持刷新token(没有返回刷新token)
 * 2.密码(password)
 * http://127.0.0.1:9300/port/user/oauth/token?grant_type=password&client_id=qizuo2&client_secret=qizuo2&password=qizuo&username=qizuo
 * 支持get和post请求；参数不要放到header中；支持刷新token
 * 3.code(authorization_code)
 * 支持get和post请求;参数不要放到header中；支持刷新token
 * a.第三方页面请求，跳转我们的登录页面http://localhost:9300/port/user/oauth/authorize?response_type=code&client_id=qizuo4&redirect_uri=http://www.baidu.com&scope=read(http://localhost:9900/user/oauth/authorize?response_type=code&client_id=qizuo4&redirect_uri=http://www.baidu.com)
 * b.自动打开登录页面输入账号密码登录(可以是自定义登录页面http://localhost:9900/user/qizuo/login)
 * c.自动跳入授权页面进行授权(可以是自定义授权页面http://localhost:9900/user/oauth/authorize?response_type=code&client_id=qizuo4&redirect_uri=http://www.baidu.com)
 * d.跳入a中redirect_uri中网页，并返回鉴权code(https://www.baidu.com/?code=FAly1t)
 * e.拿着code获取对应token(http://127.0.0.1:9900/user/oauth/token?grant_type=authorization_code&client_id=qizuo4&client_secret=qizuo4&code=Lb5Z2o&redirect_uri=http://www.baidu.com)
 * 注意：code只能获取一次，后面都要通过refresh进行刷新
 * 4.刷新(refresh_token)
 * http://127.0.0.1:9300/port/user/oauth/token?grant_type=refresh_token&client_id=qizuo2&client_secret=qizuo2&refresh_token=ey...
 * 支持get和post请求；参数不要放到header中;refresh_token要用返回的refresh_token的参数，不是token本身(所以客户端模式不支持刷新)；刷新token的参数要和获取token参数保持一致
 * 5.简化(implicit)
 * 整体步骤和授权码一致，只不过在d这一部的时候开始发生变化(response_type=token)
 * d.直接返回token：https://www.baidu.com/#access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJxaXp1byIsInNjb3BlIjpbInJlYWQiXSwibG9naW5OYW1lIjoicWl6dW8iLCJleHAiOjE2NTAxMjc0NjgsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfVVNFUkFETUlOIl0sImp0aSI6ImYyNjA1MzdiLTZlNTctNGIwOS1hYTU4LTVmMzQ2Y2YwZGIzZiIsImNsaWVudF9pZCI6InFpenVvMyIsInRpbWVzdGFtcCI6MTY0NzUzNTQ2ODk1N30.dRKPxm7pvGhCU3U1ir3cxBd8kKbgwMBueUa4YlybSEw&token_type=bearer&expires_in=2591999&scope=read&timestamp=1647535468957&loginName=qizuo&jti=f260537b-6e57-4b09-aa58-5f346cf0db3f
 * 注意：这个是没有refretoken的，也就是无法刷新
 *
 * 总结：客户端模式适合非常受信用，比如同服务器；密码模式适合亲儿子，比如同系统的登录；授权码模式适合第三方授权，因为不必透露用户名密码；简化模式用的比较少，比如一般扫码填报表啥的
 *
 * session/cookie和token两种方式认证(当前token)
 * 基于Session的认证方式由Servlet规范定制，服务端要存储session信息需要占用内存资源，客户端需要支持 cookie；(session和cookie都是服务端创建的，一个用于后台，一个返回前台，让服务端和客户端产生联系的认证方式)
 * 基于token的方式则一般不需要服务端存储token，并且不限制客户端的存储方式。如今移动互联网时代 更多类型的客户端需要接入系统，系统多是采用前后端分离的架构进行实现，所以基于token的方式更适合
 *
 * scope
 * 不论哪种模式上传，只要参数传过来scope，就会和当前数据库中scope进行校验，不相同不会返回；如果不传则不会校验(TODO 暂时SCOPE校验没有用到，后续可以加一个拦截器，鉴权路径必须传scope)
 * TODO 是否要将验证服务器放到zuul中
 *
 * @AuthenticationPrincipal 注解可以快速的获取security相关信息，比如@AuthenticationPrincipal String username
 *
 * 单点登录：
 * 原理：就是两个网站利用同一个地址登录页面，然后这个地址鉴权是根据cookie来校验的，也就是服务端的session进行校验，只要session中存有用户信息并且没有过期，网站间登录跳转就可以互通
 * oauth2的单点登录：利用授权码或者简化模式，统一登录页面，就会有上面原理，就可以多个网站单点登录了
 * 单点登录和@EnableOAuth2Sso注解有啥关系：关系不大。这个注解作用就是将所注解的服务变成oauth2入口，也就是访问注解所在服务和访问oauth2 server是一样的，也就是如果不加这个注解，直接用oauth2 server进行单点登录也没问题
 *
 * oauth2有三种鉴权token的方式：
 * 1.直接调oauth2 server接口进行鉴权
 * 2.获取tokenstore进行鉴权
 * 3.实列化tokenstore进行鉴权
 * 第二种和第三种类似，本项目用的第三种，直接实列化jwttokenstore进行鉴权；第二种是远程获取token key，调用oauth/token_key进行实列化
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  /** 认证管理器 */
  @Autowired private AuthenticationManager authenticationManager;
  /** UserDetailsService */
  @Autowired private SecurityUserDetailsSevice securityUserDetailsSevice;
  /** UserDetailsService */
  @Autowired private RestClientDetailsService restClientDetailsService;
  /** authenWebResponseExceptionTranslator 认证异常 */
//  @Autowired private AuthenWebResponseExceptionTranslator authenWebResponseExceptionTranslator;
  /** token存储 总共有四种普通/数据库/redis/jwt/ */
  @Autowired private TokenStore tokenStore;
  /** JwtAccessTokenConverter token转换器 */
  @Autowired(required = false)
  private JwtAccessTokenConverter jwtAccessTokenConverter;
  /** TokenEnhancer 附加信息放入token中 */
  @Autowired(required = false)
  private TokenEnhancer jwtTokenEnhancer;
  /** datasource */
  @Autowired private DataSource dataSource;

  /**
   * Configure.
   *
   * <p>用来配置令牌端点(Token Endpoint)的安全约束.
   * (下面两个配置主要放开token请求路径以及开启客户端模式)
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    // 所有路径拦截
    security.tokenKeyAccess("permitAll()");
    // 请求/oauth/token的，如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter
    security.allowFormAuthenticationForClients();
  }

  /**
   * Configure.
   *
   * <p>用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
   * (下面主要是配置客户端模式client/password/简化/code/refreshtoken，每种都可以存在很多个)
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //这种配置方式是写死在内存中，就是代码中，或者卸载配置文件yml中也可以
//    clients.withClientDetails(restClientDetailsService);
    //下面是配置在数据库中，后面有需求直接在数据库存入对应数据即可，注意：每次增加都必须重启
    clients.withClientDetails(new JdbcClientDetailsService(dataSource));
  }

  /**
   * Configure.
   *
   * <p>用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
   * (下面主要配置jwttoken和user验证)
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // 配置token存储/认证器/userdetailsservice/认证异常处理/获取token的请求方式
    endpoints
        .tokenStore(tokenStore)//store分为redis/jwt/jdbc三种，jwt本身串存在信息，另外两种都是存储，尤其是jdbc的存储注意：它和JDBCClientService一样，是需要预制表的，存储token相关信息(好几张表)
        .authenticationManager(authenticationManager)
        .userDetailsService(securityUserDetailsSevice)//这个地方主要是验证用户信息自实现，只是一个获取的过程，也就是这个service里面只是查询用户信息存储起来，由验证器自己验证，主要在password类别的时候进入
//        .exceptionTranslator(authenWebResponseExceptionTranslator)
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

    // jwttoken配置
    if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      List<TokenEnhancer> enhancers = new ArrayList<>();
      enhancers.add(jwtTokenEnhancer);
      enhancers.add(jwtAccessTokenConverter);
      enhancerChain.setTokenEnhancers(enhancers);
      endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
    }
  }

  /**
   * 退出时的处理策略配置
   *
   * @return logout success handler
   */
//  @Bean
//  public LogoutSuccessHandler logoutSuccessHandler() {
//    return new AuthenLogoutSuccessHandler();
//  }
}
