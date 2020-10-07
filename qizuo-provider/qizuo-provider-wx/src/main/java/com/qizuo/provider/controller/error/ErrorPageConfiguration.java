package com.qizuo.provider.controller.error;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 配置错误状态与对应访问路径
 * </pre>
 */
@Component
public class ErrorPageConfiguration implements ErrorPageRegistrar {
  @Override
  public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
    errorPageRegistry.addErrorPages(
        new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"),
        new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500")
    );
  }

}
