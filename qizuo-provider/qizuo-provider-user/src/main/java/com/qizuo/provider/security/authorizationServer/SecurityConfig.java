/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.authorizationServer;

import com.qizuo.provider.security.authorizationServer.doResult.AuthenLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/** security基本配置器，对sercurity基本属性一些配置，比如登录页面，登录url，登出url等等,就是没有spring security oath2的时候会用到这个，这个主要用作授权码自定义登录页面和授权页面*/
//WebSecurityConfigurerAdapter和ResourceServerConfigurerAdapter主要区别，前者这里主要是自定义表单登录(自定义授权只要复写controller即可，跟这个没关系)；后者主要是oauth2资源服务器token认证
//这个地方配置主要是授权码模式和简化模式使用
@Configuration
@EnableWebSecurity
//这个注解在这个地方主要提高当前filter级别，要不然ResourceServerConfigurerAdapter级别比WebSecurityConfigurerAdapter高(3)，这样就会导致所有请求先进后面的filter(authorizeRequests配置后)，然后当前filter HttpSecurity配置不会生效
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  //入口地址
  @Value("${port_url}")
  String port_url;
  /**
   * Configure.
   * 注意：
   * authorizeRequests.antMatchers("/favicon.icon").permitAll()代表这个路径放权，其它的路径都需要放权
   * authorizeRequests.antMatchers("/favicon.icon").authenticated()代表这个路径需要验证，其它的路径都需要放权
   * .anyRequest().authenticated()如果在上面后跟上这个，代表除了那个路径外，其它路径都需要鉴权，换成permitAll则是都放权
   * .antMatcher("/login/**").authorizeRequests.antMatchers("/login/favicon.icon").authenticated()代表/login/**这个路径下的/login/favicon.icon鉴权，其它的(包括不属于这个路径)都需要放权
   * requestMatchers().antMatchers("/login/**").and()加上这个代表这个拦截器只会处理上面路径请求，和antMatcher类似，只不过它是多个
   * 注意上面这些写法的顺序很关键，不要顺序弄错了
   * 通过上面配置结论：只要在authorizeRequests前面加上antMatcher或者requestMatchers().antMatchers("/login/**")这种类似，当前这个filter只会处理这些路径，否则一旦filter HttpSecurity加上authorizeRequests就会对所有请求进行处理
   * 结论2：security这种验证filter只要路径匹配上就会进去其中一个，并且仅进入一个，进谁按照上面说的这种路径进行匹配，相同路径下，谁的filter Order顺序大，进谁！比如当前这个WebSecurityConfigurerAdapter和ResourceServerConfigurerAdapter，后面一个优先级大是3，也就是相同情况下先进后面，除非你用@order进行优先级调整
   * 结论3：每个filter中的HttpSecurity都是独立的，也就是独立配置的，不要指望别的filter中配置有啥影响
   * TODO 经过zuul的授权码模式无法使用，主要因为自定义登录页面和授权路径问题
   * @param http the http
   * @throws Exception the exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers()
        .frameOptions()
        .disable() // 请求头设置，这个地方是配置iframe权限，springSecurty使用X-Frame-Options防止网页被Frame，这是去掉
        .and() // and()返回一个SecurityBuilder。
        // httpBasic是利用HTTP头部进行认证，访问页面时会由浏览器弹框要求密码，这个是走HTTP协议层面的认证
        // formLogin是基于页面，你需要自己实现一个登录页面，也就是示例中的/usercheck.jsp（名字你可以自己定），里面要有一个登录表单，表单的action和用户名
        // 密码字段名都是框架定死的，然后你需要再实现一个servlet来处理这个表单的action，实现登录，实际上走的是session/cookie认证
        .formLogin() // Spring Security支持两种认证方式：formLogin()和httpBasic()。
        .loginPage(port_url+"user/qizuo/login")//自定义登录页面：登陆界面页面跳转URL/qizuo/login
        .loginProcessingUrl("/qizuo/authorize")//自定义登录页面：这个地方必须要登录页面提交的请求路径保持一致，这个实际上是没有controller的，是会被拦截的读取的伪路径(注意如果出现404，肯定是其它的拦截拦截了这个伪路径，想办法去掉这个拦截器，比如token的，还有资源服务器验证的等)
        .and()
//        .logout()
//        .logoutUrl("/logout") // 发起登出请求的URL
//        .logoutSuccessHandler(new AuthenLogoutSuccessHandler())
//        .and()
        .csrf()
        .disable()// 关闭默认打开的crsf protection，因为打开这个会对自制的一些token认证功能有影响
        .requestMatchers().antMatchers("/qizuo/**","/oauth/authorize").and()//这个地方表明当前filter只会有上述请求进入，其它的请求还是需要资源服务器ResourceServerConfigurerAdapter处理token认证
        .authorizeRequests() // 启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护，为后面设置启用
        .antMatchers("/favicon.icon","/qizuo/login") // 未登陆用户允许的请求 /**/*.css ,"/**/qizuo/**"
        .permitAll() // 未登陆用户允许的请求
        .anyRequest()
        .authenticated(); // 其他请求全部需要登陆
  }
}
