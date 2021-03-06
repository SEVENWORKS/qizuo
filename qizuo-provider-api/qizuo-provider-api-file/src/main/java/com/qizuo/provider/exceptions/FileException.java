/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.exceptions;

import com.qizuo.base.exception.BusinessException;
import com.qizuo.config.properties.baseProperties.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

/** user exception. */
@Slf4j
public class FileException extends BusinessException {

  private static final long serialVersionUID = -6552248511084911254L;

  /** Instantiates a new Uac rpc exception. */
  public FileException() {}

  /**
   * Instantiates a new Uac rpc exception.
   *
   * @param code the code
   * @param msgFormat the msg format
   * @param args the args
   */
  public FileException(int code, String msgFormat, Object... args) {
    super(code, msgFormat, args);
    log.info("<== FileException, code:{}, message:{}", this.code, super.getMessage());
  }

  /**
   * Instantiates a new Uac rpc exception.
   *
   * @param code the code
   * @param msg the msg
   */
  public FileException(int code, String msg) {
    super(code, msg);
    log.info("<== FileException, code:{}, message:{}", this.code, super.getMessage());
  }

  /**
   * Instantiates a new Mdc rpc exception.
   *
   * @param codeEnum the code enum
   */
  public FileException(ResultCodeEnum codeEnum) {
    super(codeEnum.code(), codeEnum.msg());
    log.info("<== FileException, code:{}, message:{}", this.code, super.getMessage());
  }

  /**
   * Instantiates a new Mdc rpc exception.
   *
   * @param codeEnum the code enum
   * @param args the args
   */
  public FileException(ResultCodeEnum codeEnum, Object... args) {
    super(codeEnum, args);
    log.info("<== FileException, code:{}, message:{}", this.code, super.getMessage());
  }
}
