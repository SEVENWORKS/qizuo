/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.message;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** message vo(视图对象). */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MqMessageVo extends BasePoJo {

  private static final long serialVersionUID = 5440371083922622116L;
  /** 消息key */
  @ApiModelProperty(value = "消息key")
  private String messageKey;

  /** topic */
  @ApiModelProperty(value = "topic")
  private String messageTopic;

  /** tag */
  @ApiModelProperty(value = "tag")
  private String messageTag;

  /** 消息内容 */
  @ApiModelProperty(value = "消息内容")
  private String messageBody;

  /** 消息类型: 10 - 生产者 ; 20 - 消费者 */
  @ApiModelProperty(value = "消息类型: 10 - 生产者 ; 20 - 消费者")
  private Integer messageType;

  /** 顺序类型, 0有序 1无序 */
  @ApiModelProperty(value = "顺序类型, 0有序 1无序")
  private int orderType;

  /** 消息状态 */
  @ApiModelProperty(value = "消息状态")
  private Integer status;

  /** 延时级别 */
  @ApiModelProperty(value = "延时级别")
  private int delayLevel;
}
