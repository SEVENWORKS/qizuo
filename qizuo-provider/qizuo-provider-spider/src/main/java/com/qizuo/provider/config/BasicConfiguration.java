package com.qizuo.provider.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Site;

/** 基本文件配置 */
public class BasicConfiguration extends AbstractConfiguration {
  protected Site site; // 地址

  protected String baseDir; // 文件储藏位置

  public BasicConfiguration(String path) {
    super(path);
  }

  public BasicConfiguration() {}

  @Override
  protected void resolve() {
    JSONObject jsonObject = JSON.parseObject(config);
    site = JSON.parseObject(jsonObject.getString("site"), Site.class);
    setBaseDir(jsonObject.getString("base_dir"));
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
}
