/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.admin;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;

//springboot启动
@SpringBootApplication
//eureka client启动
@EnableDiscoveryClient
//admin server启动
@EnableAdminServer
//turbine启动(断路器多点监控)
@EnableTurbine
//断路器可视化工具启动
@EnableHystrixDashboard
//断路器启动
@EnableCircuitBreaker
public class QiZuoAdminApplication {
	/**
	 * hazelcast缓存
	 */
	//作用于方法上，产生一个bean，交给spring容器
	@Bean
	public Config hazelcastConfig() {
		return new Config().setProperty("hazelcast.jmx", "true")
				.addMapConfig(new MapConfig("spring-boot-admin-application-store").setBackupCount(1)
						.setEvictionPolicy(EvictionPolicy.NONE))
				.addListConfig(new ListConfig("spring-boot-admin-event-store").setBackupCount(1)
						.setMaxSize(1000));
	}

	public static void main(String[] args) {
		SpringApplication.run(QiZuoAdminApplication.class, args);
	}
}
