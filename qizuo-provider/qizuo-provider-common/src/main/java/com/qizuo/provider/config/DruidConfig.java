/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Druid监控.http://localhost:9900/user/druid/index.html(注意这地方和yml中配置会覆盖，这地方为先) */
@Configuration
public class DruidConfig {
  // 监控
  @Bean
  public ServletRegistrationBean druidServlet() { // 主要实现web监控的配置处理
    ServletRegistrationBean servletRegistrationBean =
        new ServletRegistrationBean(new StatViewServlet(), "/druid/*"); // 表示进行druid监控的配置处理操作
    servletRegistrationBean.addInitParameter("allow", ""); // 白名单
    servletRegistrationBean.addInitParameter("deny", ""); // 黑名单
    servletRegistrationBean.addInitParameter("loginUsername", "qizuo"); // 用户名
    servletRegistrationBean.addInitParameter("loginPassword", "qizuo"); // 密码
    servletRegistrationBean.addInitParameter("resetEnable", "false"); // 是否可以重置数据源
    return servletRegistrationBean;
  }

  // 监控
  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new WebStatFilter());
    filterRegistrationBean.addUrlPatterns("/*"); // 所有请求进行监控处理
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*"); // 排除
    return filterRegistrationBean;
  }
}
