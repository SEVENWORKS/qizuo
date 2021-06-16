/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.generator;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;

import java.util.List;

/** 生成器执行类. */
public class Generator extends PluginAdapter {

  /** validate方法调用，该方法一般用于验证传给参数的正确性，如果该方法返回false，则该插件结束执行； */
  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  /** 调用方法 */
  public static void main(String[] args) {
    generate();
  }

  /** 生成方法 */
  private static void generate() {
    // 获取配置文件地址
    String config =
        com.qizuo.generator.Generator.class
            .getClassLoader()
            .getResource("generator/generatorConfig.xml")
            .getFile();
    // 生成参数
    String[] arg = {"-configfile", config, "-overwrite"};
    // 执行生成
    ShellRunner.main(arg);
  }
}
