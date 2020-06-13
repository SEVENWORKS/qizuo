/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.zuul.restTemplateHeader;

import com.qizuo.config.properties.baseProperties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Spring RestTemplate经常被用作客户端向Restful API发送各种请求，也许你也碰到过这种需求，很多请求都需要用到相似或者相同的Http
 * Header。如果在每次请求之前都把Header填入HttpEntity/RequestEntity，这样的代码会显得十分冗余。
 *
 * <p>Spring提供了ClientHttpRequestInterceptor接口，可以对请求进行拦截，并在其被发送至服务端之前修改请求或是增强相应的信息
 *
 * <p>这个和authHeaderfilter做的事情其实一样的,只不过它是针对的spring RestTemplate做的请求头设置
 */
@Component
@Slf4j
public class HttpRequestInterceptor implements ClientHttpRequestInterceptor {
  /** response的时候添加header. */
  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
    // 把header集合变成指定字符串
    String header =
        StringUtils.collectionToDelimitedString(
            HeaderInterceptor.LABEL.get(), GlobalConstant.Symbol.COMMA);
    log.info("header={} ", header);
    // 添加一个header
    requestWrapper.getHeaders().add(GlobalConstant.HttpConfig.HEADER_ZUUL, header);
    return execution.execute(requestWrapper, body);
  }
}
