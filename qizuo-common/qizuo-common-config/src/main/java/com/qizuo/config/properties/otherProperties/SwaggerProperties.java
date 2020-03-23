/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.config.properties.otherProperties;

import lombok.Data;

/**
 * Swagger properties.
 */
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "无痕";

	private String contactUrl = "http://qizuo.net";

	private String contactEmail = "a554657207@126.com";
}
