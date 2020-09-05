/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/** message dto(数据传输对象). */
@Data
// 自动生成全参数构造函数
@AllArgsConstructor
@ApiModel
public class MqMessageDto implements Serializable {

  private static final long serialVersionUID = -995670498005087805L;
  /** 消息key */
  @ApiModelProperty(value = "消息key")
  private String messageKey;

  /** topic */
  @ApiModelProperty(value = "topic")
  private String messageTopic;
}
