/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.authorizationServer;

import com.qizuo.provider.security.authorizationServer.service.RestClientDetailsService;
import com.qizuo.security.service.SecurityUserDetailsSevice;
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
 *
 * @PreAuthorize("hasAuthority('admin')")、@PreAuthorize("hasRole('admin')")
 * @PreAuthorize("#oauth2.hasScope('read')")
 * 这个注解可以用到类或者方法上都行，但是注解是不能重复的，并且方法会覆盖类上的配置，即如果配置两个，后面会覆盖前面的
 * 上述注解需要@EnableGlobalMethodSecurity(prePostEnabled = true)开启，这个要放到开启的类上面(如果是admin角色验证，可以不需要，因为resource上开启了，但是最好还是统一加上)
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
