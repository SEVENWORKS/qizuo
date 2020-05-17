/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.util.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis工具.
 */

/**
 *
 @Autowired
 RedisTemplate<String, String> redisTemplate;
 @ResponseBody
 @RequestMapping("hehe")
 public String base(){
 RedisUtil.delete("1",redisTemplate);
 System.out.println(RedisUtil.get("1",redisTemplate));
 return "33";
 }
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisUtil {
	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public static String get(final String key,RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 写入缓存
	 */
	public static boolean set(final String key, String value,RedisTemplate<String, String> redisTemplate) {
		boolean result = false;
		try {
			redisTemplate.opsForValue().set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新缓存
	 */
	public static boolean getAndSet(final String key, String value,RedisTemplate<String, String> redisTemplate) {
		boolean result = false;
		try {
			redisTemplate.opsForValue().getAndSet(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除缓存
	 */
	public static boolean delete(final String key,RedisTemplate<String, String> redisTemplate) {
		boolean result = false;
		try {
			redisTemplate.delete(key);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
