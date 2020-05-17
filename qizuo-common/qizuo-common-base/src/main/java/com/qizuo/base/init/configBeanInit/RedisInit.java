/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.init.configBeanInit;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis初始化.
 */
@Configuration
//开启缓存
@EnableCaching
public class RedisInit {
	/**
	 * redis key生成.
	 *
	 * @return the key generator
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};

	}

	/**
	 * Cache manager cache manager.
	 * 整合Redis非关系数据库作为内存缓存框，替换springcahe
	 *
	 Spring cache是代码级的缓存，一般是使用一个ConcurrentMap，也就是说实际上还是是使用JVM的内存来缓存对象的，这势必会造成大量的内存消耗。但好处是显然的：使用方便。
	 Redis 作为一个缓存服务器，是内存级的缓存。它是使用单纯的内存来进行缓存。
	 集群环境下，每台服务器的spring cache是不同步的，这样会出问题的，spring cache只适合单机环境。
	 Redis是设置单独的缓存服务器，所有集群服务器统一访问redis，不会出现缓存不同步的情况。
	 * @return the cache manager
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	/**
	 *StringBoot 整合Redis key解决存储乱码（通过StringRedisSerializer来进行序列化）
	 */
	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}

	@Bean("redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		//key的序列化方式
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//设置value序列化方式
		template.setValueSerializer(jackson2JsonRedisSerializer);
		//设置key序列化方式，解决存储乱码
		template.setKeySerializer(stringRedisSerializer());
		//非spring注入使用RedisTemplate,需先调用afterPropertiesSet()方法
		template.afterPropertiesSet();
		return template;
	}
}
