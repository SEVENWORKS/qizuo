/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.util.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/** redis工具. */

/**
 * @Autowired RedisTemplate<String, String> redisTemplate; @ResponseBody @RequestMapping("hehe")
 * public String base(){ RedisUtil.delete("1",redisTemplate);
 * System.out.println(RedisUtil.get("1",redisTemplate)); return "33"; }
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisUtil {
  @Autowired private static RedisTemplate redisTemplate;
  /**
   * 读取缓存
   *
   * @param key
   * @return
   */
  public static Object get(final String key) {
    return redisTemplate.opsForValue().get(key);
  }

  /** 写入缓存 */
  public static boolean set(final String key, Object value) {
    boolean result = false;
    try {
      redisTemplate.opsForValue().set(key, value);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /** 写入缓存 */
  public static boolean set(final String key, Object value, int time) {
    boolean result = false;
    try {
      redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /** 更新缓存 */
  public static boolean getAndSet(final String key, Object value) {
    boolean result = false;
    try {
      redisTemplate.opsForValue().getAndSet(key, value);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /** 删除缓存 */
  public static boolean delete(final String key) {
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
