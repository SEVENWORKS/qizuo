/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config.properties.otherProperties;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.Data;

/** Swagger properties yaml.带y的表示是和yaml对应的，并且包含基本启动 */
@Data
public class SwaggerPropertiesGY {

  private String title;

  private String description;

  private String version = "1.0";

  private String license = "tokenGet";

  private String licenseUrl = "http://www.baidu.com";

  private String contactName = GlobalConstant.Global;

  private String contactUrl = "http://www.baidu.com";

  private String contactEmail = "a554657207@126.com";
}
