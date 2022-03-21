package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** @Author: fangl @Description: 消息 @Date: 14:20 2018/10/29 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MsgPoJo extends BasePoJo {
  /** 消息名称 */
  @ApiModelProperty(value = "消息名称")
  private String title;
  /** 消息类型 */
  @ApiModelProperty(value = "消息类型")
  private String type;
  /** 消息内容 */
  @ApiModelProperty(value = "消息内容")
  private String content;
  /** 接受人id */
  @ApiModelProperty(value = "接受人id")
  private String sendUserId;
  /** 接受人类型 */
  @ApiModelProperty(value = "接受人类型")
  private String sendType;
  /** 是否发送 */
  @ApiModelProperty(value = "是否发送")
  private int isSend;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSendUserId() {
    return sendUserId;
  }

  public void setSendUserId(String sendUserId) {
    this.sendUserId = sendUserId;
  }

  public String getSendType() {
    return sendType;
  }

  public void setSendType(String sendType) {
    this.sendType = sendType;
  }

  public int getIsSend() {
    return isSend;
  }

  public void setIsSend(int isSend) {
    this.isSend = isSend;
  }
}
