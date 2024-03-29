/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.utils.token;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/** token store配置:jwttokenstore和redistokenstore. */
@Configuration
public class TokenStoreConfig {

  /** 使用redis存储token的配置，只有在paascloud.security.oauth2.tokenStore配置为redis时生效 这种存储需要redis配合 */
  @Configuration
  @ConditionalOnProperty(
    prefix = "qizuo.security.oauth2",
    name = "tokenStore",
    havingValue = "redis"
  )
  public static class RedisConfig {

    @Autowired private RedisConnectionFactory redisConnectionFactory;

    /**
     * Redis token store token store. 构建一个redis store
     *
     * @return token store
     */
    @Bean("TokenStore")
    public TokenStore redisTokenStore() {
      return new RedisTokenStore(redisConnectionFactory);
    }
  }

  /** 使用jwt时的配置，默认生效 这种存储不需要实际上的空间存储，它的解析和信息都放在令牌中，matchIfMissing没有默认的值 */
  @Configuration
  @ConditionalOnProperty(
    prefix = "qizuo.security.oauth2",
    name = "tokenStore",
    havingValue = "jwt",
    matchIfMissing = true
  )
  public static class JwtConfig {

    /**
     * Jwt token store token store.token存储 构建一个jwtstore jwt解析器
     *
     * @return the token store
     */
    @Bean("TokenStore")
    public TokenStore jwtTokenStore() {
      return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * Jwt access token converter jwt access token converter. 转换token jwt解析器核心
     *
     * @return the jwt access token converter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      converter.setSigningKey(GlobalConstant.Global);
      return converter;
    }

    /**
     * Jwt token enhancer token enhancer.附加信息放入token中 jwt生成基本配置
     *
     * @return the token enhancer
     */
    @Bean
    @ConditionalOnBean(TokenEnhancer.class) // 判断如果存在这个类就进行初始化
    public TokenEnhancer jwtTokenEnhancer() {
      return new TokenJwtEnhancer();
    }
  }
}
