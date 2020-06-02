/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.provider.security.authorizationServer;

import com.qizuo.provider.security.service.SecurityClientDetailsSevice;
import com.qizuo.provider.security.model.SecurityUser;
import com.qizuo.provider.security.service.SecurityUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
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
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.ArrayList;
import java.util.List;


/**
 * 授权服务器的配置核心，统筹整个服务配置，包括token存储，认证管理
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/**
	 * token存储 总共有四种普通/数据库/redis/jwt/
	 */
	@Autowired
	private TokenStore tokenStore;
	/**
	 * 认证管理器
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	/**
	 * UserDetailsService
	 */
	@Autowired
	private SecurityUserDetailsSevice securityUserDetailsSevice;
	/**
	 * UserDetailsService
	 */
	@Autowired
	private SecurityClientDetailsSevice securityClientDetailsSevice;

	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;


	/**
	 * tokenStore
	 */
	@Bean(name = "tokenStore")
	public TokenStore tokenStore(JedisConnectionFactory jedisConnectionFactory) {
		RedisTokenStore redis = new RedisTokenStore(jedisConnectionFactory);
		return redis;
	}

	/**
	 * Configure.
	 *
	 * 用来配置令牌端点(Token Endpoint)的安全约束.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
		security.allowFormAuthenticationForClients();
	}

	/**
	 * Configure.
	 *
	 * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(securityClientDetailsSevice);
	}

	/**
	 * Configure.
	 *
	 * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(securityUserDetailsSevice);
		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(enhancers);
			endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
		}
	}
}
