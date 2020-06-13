/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.baseProperties;

/** 框架基本全局配置参数 */
public class GlobalConstant {
  /** 全局YES */
  public static final String YES = "1";
  /** 全局NO */
  public static final String NO = "0";
  /** 有效 */
  public static final String STATUS_YES = "0";
  /** 无效 */
  public static final String STATUS_NO = "1";
  /** 全局常用 */
  public static final String Global = "qizuo";

  /** 常用角色配置 */
  public static final class Role {
    private Role() {}

    /** 超级管理员的用户 */
    public static final String SUPER = GlobalConstant.Global;
    /** 普通用户 */
    public static final String COMMON_USER = "common_user";
  }

  /** 数据权限. */
  public static final class DataRole {
    private DataRole() {}
    /** 增 */
    public static final String DATAROLE_INSERT = "0";
    /** 删 */
    public static final String DATAROLE_DELETE = "1";
    /** 查 */
    public static final String DATAROLE_QUERY = "2";
    /** 改 */
    public static final String DATAROLE_UPDATE = "3";
  }

  /** 常用符号类 */
  public static final class Symbol {
    private Symbol() {}

    public static final String COMMA = ",";
    public static final String SPOT = ".";
    public static final String UNDER_LINE = "_";
    public static final String PER_CENT = "%";
    public static final String AT = "@";
    public static final String PIPE = "||";
    public static final String SHORT_LINE = "-";
    public static final String SPACE = " ";
    public static final String SLASH = "/";
    public static final String MH = ":";
  }

  /** url和path常用配置文件. */
  public static final class Url$Path {
    private Url$Path() {}
    // 不走token权限验证的url
    public static final String TokenInterceptor_AUTH_PATH = "/error"; // swagger-ui.html
    // 配置前缀
    public static final String ROOT_PREFIX = GlobalConstant.Global;
    // token相关路径
    public static final String TokenInterceptor_SECURITY_PATH = "/oauth/token";
    public static final String TokenInterceptor_SECURITY_PATH2 = "/oauth/authorize";
  }

  /** http常用配置文件. */
  public static final class HttpConfig {
    private HttpConfig() {}
    // zuul标识头
    public static final String HEADER_ZUUL = "x-qizuo";
    // requestutil中配置
    public static final String UNKNOWN = "unknown";
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_REAL_IP = "X-Real-IP";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    public static final String LOCALHOST_IP = "127.0.0.1";
    public static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
    public static final int MAX_IP_LENGTH = 15;
    // 最大请求头数量
    public static final int LogAspect_MAX_SIZE = 2000;
    // auth请求头截取
    public static final String AUTH_HEADER_SPLIT = "bearer ";
  }

  /** 媒体文件常用配置 */
  public static final class Media {
    private Media() {}
  }

  /** 常用数字配置 */
  public interface Number {
    // 1000
    int THOUSAND_INT = 1000;
  }

  /** 常用sql配置 */
  public static final class SqlConfig {
    private SqlConfig() {}
    // 不需要拦截器打印sql的标志
    public static final String NotDisplaySqlAspect_DISPLAY_SQL = "DISPLAY_SQL";
    // sql执行时间设置，大于这个时间就会报错error
    public static final Double SqlLogInterceptor_NoticeTime = 10.0;
  }

  /** 常用编码配置 */
  public static final class Encode {
    private Encode() {}

    public static final String ENCODE = "GBK";
    public static final String CHAR_SET = "UTF-8";
  }

  /** 常用redis keys */
  public static final class RedisCode {
    private RedisCode() {}
  }
}
