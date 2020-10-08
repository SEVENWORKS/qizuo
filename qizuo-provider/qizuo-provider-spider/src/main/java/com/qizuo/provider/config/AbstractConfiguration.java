package com.qizuo.provider.config;

import com.qizuo.provider.utils.FileHelper;

/** 抽象配置文件类 */
public abstract class AbstractConfiguration {

  public static final String DEFAULT_CONFIG_FILE = "config/config.json"; // 配置文件名
  public static final String DEFAULT_CONFIG_DIR =
      AbstractConfiguration.class.getResource("/").getPath() + "/"; // 配置文件位置

  protected String config; // 配置文件读取

  protected AbstractConfiguration() {
    this(DEFAULT_CONFIG_DIR + DEFAULT_CONFIG_FILE);
  }

  protected AbstractConfiguration(String path) {
    config = FileHelper.getRawText(path);
    resolve();
  }

  protected abstract void resolve();

  public String getConfig() {
    return config;
  }
}
