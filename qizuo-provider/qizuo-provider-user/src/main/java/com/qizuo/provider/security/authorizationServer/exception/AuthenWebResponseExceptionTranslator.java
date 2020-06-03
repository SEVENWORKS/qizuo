/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.authorizationServer.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.util.HtmlUtils;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Map;

/** 自定义 ExceptionTranslator 实现认证服务器的异常信息处理. */
@Configuration
public class AuthenWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
  private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
  /**
   * Translate response entity.
   *
   * @param e the e
   * @return the response entity
   * @throws Exception the exception
   */
  @Override
  public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
    // Try to extract a SpringSecurityException from the
    // stacktrace，从异常栈中获取exception类型，如果是oath2类型的，就进行异常信息处理
    Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

    // 异常栈获取 OAuth2Exception 异常
    Exception ase =
        (OAuth2Exception)
            throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

    // 异常栈中有OAuth2Exception
    if (ase != null) {
      return handleOAuth2Exception((OAuth2Exception) ase);
    }

    // AuthenticationException
    ase =
        (AuthenticationException)
            throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
    if (ase != null) {
      return handleOAuth2Exception(new UnauthorizedClientException(e.getMessage(), e));
    }

    // AccessDeniedException
    ase =
        (AccessDeniedException)
            throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
    if (ase instanceof AccessDeniedException) {
      return handleOAuth2Exception(new OAuth2Exception(ase.getMessage(), ase));
    }

    // HttpRequestMethodNotSupportedException
    ase =
        (HttpRequestMethodNotSupportedException)
            throwableAnalyzer.getFirstThrowableOfType(
                HttpRequestMethodNotSupportedException.class, causeChain);
    if (ase instanceof HttpRequestMethodNotSupportedException) {
      return handleOAuth2Exception(new OAuth2Exception(ase.getMessage(), ase));
    }

    // 不包含上述异常则服务器内部错误,即默认
    return handleOAuth2Exception(
        new OAuth2Exception(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));
  }

  private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e)
      throws IOException {

    int status = e.getHttpErrorCode();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Cache-Control", "no-store");
    headers.set("Pragma", "no-cache");
    if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
      headers.set(
          "WWW-Authenticate",
          String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
    }

    BootOAuth2Exception exception = new BootOAuth2Exception(e.getMessage(), e);

    ResponseEntity<OAuth2Exception> response =
        new ResponseEntity<OAuth2Exception>(exception, headers, HttpStatus.valueOf(status));
    return response;
  }

  // 下面是自定义的序列化

  // 序列化BootOAuth2Exception这个类，加上注解，指定用于序列化的类
  @JsonSerialize(using = BootOAuthExceptionJacksonSerializer.class)
  public class BootOAuth2Exception extends OAuth2Exception {
    public BootOAuth2Exception(String msg, Throwable t) {
      super(msg, t);
    }

    public BootOAuth2Exception(String msg) {
      super(msg);
    }
  }

  // 序列化返回的oauth2exception
  public class BootOAuthExceptionJacksonSerializer extends StdSerializer<BootOAuth2Exception> {

    protected BootOAuthExceptionJacksonSerializer() {
      super(BootOAuth2Exception.class);
    }

    @Override
    public void serialize(
        BootOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider)
        throws IOException {
      // 序列化开始
      jgen.writeStartObject();
      // status字段塞入
      jgen.writeObjectField("status", value.getHttpErrorCode());
      // errormessage塞入
      String errorMessage = value.getOAuth2ErrorCode();
      if (errorMessage != null) {
        errorMessage = HtmlUtils.htmlEscape(errorMessage);
      }
      jgen.writeStringField("msg", errorMessage);
      // 其它信息字段塞入
      if (value.getAdditionalInformation() != null) {
        for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
          String key = entry.getKey();
          String add = entry.getValue();
          jgen.writeStringField(key, add);
        }
      }

      // 序列化结束
      jgen.writeEndObject();
    }
  }
}
