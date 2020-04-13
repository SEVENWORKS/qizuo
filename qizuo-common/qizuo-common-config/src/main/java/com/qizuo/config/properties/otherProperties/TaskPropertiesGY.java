/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.config.properties.otherProperties;

import lombok.Data;

/**
 * 定时properties.
 */
@Data
public class TaskPropertiesGY {

	private int corePoolSize = 50;

	private int maxPoolSize = 100;

	private int queueCapacity = 10000;

	private int keepAliveSeconds = 3000;

	private String threadNamePrefix = "qizuo-task-executor-";
}
