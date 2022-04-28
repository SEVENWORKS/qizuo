/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.zuul.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 有一些预先数据的加载，实现CommandLineRunner，order控制执行顺序
 */
@Component
//执行顺序
@Order(value = 1)
@Slf4j
public class InitConfig implements CommandLineRunner {

	/**
	 * Run.
	 */
	@Override
	public void run(String... args) {
		log.info(">>>>>>>>>>>>>>>服务启动执行 <<<<<<<<<<<<<");
	}

}