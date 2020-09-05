/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.message;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/** @Author: fangl @Description: 消息 @Date: 14:20 2018/10/29 */
@Data
@ApiModel
public class MsgPoJo extends BasePoJo {
  /** 标题 */
  @ApiModelProperty(value = "标题")
  private String title;
  /** 内容 */
  @ApiModelProperty(value = "内容")
  private String content;
  /** 消息跳转url */
  @ApiModelProperty(value = "消息跳转url")
  private String jumpUrl;
  /** 类型 */
  @ApiModelProperty(value = "类型")
  private String type;
  /** 发送所有关联 */
  @ApiModelProperty(value = "发送所有关联")
  private String sendTypeId;

  @ApiModelProperty(value = "发送所有关联 name")
  private String sendTypeNm;
  /** 发送单个关联 */
  @ApiModelProperty(value = "发送单个关联")
  private String sendUserId;

  @ApiModelProperty(value = "发送单个关联 name")
  private String sendUserNm;
  /** 发送多个关联 */
  @ApiModelProperty(value = "发送多个关联")
  private String sendUserIds;

  @ApiModelProperty(value = "信息")
  private List<MsgPoJo> msgPoJos;
  /** 是否已读 */
  @ApiModelProperty(value = "是否已读")
  private String isRead;
}
