/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.authorizationServer;

import com.qizuo.provider.security.authorizationServer.doResult.AuthenLogoutSuccessHandler;
import com.qizuo.provider.security.authorizationServer.exception.AuthenWebResponseExceptionTranslator;
import com.qizuo.provider.security.service.SecurityClientDetailsSevice;
import com.qizuo.provider.security.service.SecurityUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器的配置核心，统筹整个服务配置，包括token存储，认证管理
 *
 * <p>请求优先由@EnableAuthorizationServer、@EnableResourceServer 处理，剩下的无法匹配的由SpringSecurity处理。
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  /** 认证管理器 */
  @Autowired private AuthenticationManager authenticationManager;
  /** UserDetailsService */
  @Autowired private SecurityUserDetailsSevice securityUserDetailsSevice;
  /** UserDetailsService */
  @Autowired private SecurityClientDetailsSevice securityClientDetailsSevice;
  /** authenWebResponseExceptionTranslator 认证异常 */
  @Autowired private AuthenWebResponseExceptionTranslator authenWebResponseExceptionTranslator;
  /** token存储 总共有四种普通/数据库/redis/jwt/ */
  @Autowired private TokenStore tokenStore;
  /** JwtAccessTokenConverter token转换器 */
  @Autowired(required = false)
  private JwtAccessTokenConverter jwtAccessTokenConverter;
  /** TokenEnhancer 附加信息放入token中 */
  @Autowired(required = false)
  private TokenEnhancer jwtTokenEnhancer;

  /**
   * Configure.
   *
   * <p>用来配置令牌端点(Token Endpoint)的安全约束.
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
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(securityClientDetailsSevice);
  }

  /**
   * Configure.
   *
   * <p>用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // 配置token存储/认证器/userdetailsservice/认证异常处理
    endpoints
        .tokenStore(tokenStore)
        .authenticationManager(authenticationManager)
        .userDetailsService(securityUserDetailsSevice)
        .exceptionTranslator(authenWebResponseExceptionTranslator);

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
  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new AuthenLogoutSuccessHandler();
  }
}
