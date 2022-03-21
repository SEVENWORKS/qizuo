package com.qizuo.provider.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qizuo.provider.utils.FileHelper;
import us.codecraft.webmagic.Site;

/** 基本文件配置(主要是config.json文件配置) */
public class BasicConfiguration{
  public static final String DEFAULT_CONFIG_FILE = "config/config.json"; // 配置文件名
  public static final String DEFAULT_CONFIG_DIR =
          BasicConfiguration.class.getResource("/").getPath() + "/"; // 配置文件位置
  protected String config; // 配置文件全部
  protected Site site; // 配置文件爬取文件
  protected String baseDir; // 配置文件爬取存放文件
  public BasicConfiguration(String path) {
    config = FileHelper.getRawText(path);
    resolve();
  }
  public BasicConfiguration() {
    this(DEFAULT_CONFIG_DIR + DEFAULT_CONFIG_FILE);
  }

  private void setBaseDir(String directory) {
    baseDir = directory.endsWith("/") ? directory : directory + "/";
  }
  public String getBaseDir() {
    return baseDir;
  }
  public Site getSite() {
    return site;
  }

  protected void resolve() {
    JSONObject jsonObject = JSON.parseObject(config);
    site = JSON.parseObject(jsonObject.getString("site"), Site.class);
    setBaseDir(jsonObject.getString("base_dir"));
  }
}
