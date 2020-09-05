/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** 组织/系统日志container */
@Data
@ApiModel(value = "日志操作类")
public class LogDto implements Serializable {
  private static final long serialVersionUID = -5606865665592482762L;

  @ApiModelProperty(value = "id")
  private String id;
  /** 日志内容 */
  @ApiModelProperty(value = "日志内容")
  private String content;
  /** 日志类型 */
  @ApiModelProperty(value = "日志类型")
  private Enum type;

  @ApiModelProperty(value = "日志类型 id")
  private String typeCd;

  @ApiModelProperty(value = "日志类型 name")
  private String typeName;

  /** 以下是其他可能出现信息 */
  /** 组织流水号 */
  @ApiModelProperty(value = "组织流水号")
  private String groupId;

  /** 组织名称 */
  @ApiModelProperty(value = "组织名称")
  private String groupName;

  /** 日志类型 */
  @ApiModelProperty(value = "日志类型")
  private String logType;

  /** 日志类型名称 */
  @ApiModelProperty(value = "日志类型名称")
  private String logName;

  /** 权限ID */
  @ApiModelProperty(value = "权限ID")
  private Long actionId;

  /** 权限编码 */
  @ApiModelProperty(value = "权限编码")
  private String actionCode;

  /** 权限名称 */
  @ApiModelProperty(value = "权限名称")
  private String actionName;

  /** 操作系统 */
  @ApiModelProperty(value = "操作系统")
  private String os;

  /** 浏览器类型 */
  @ApiModelProperty(value = "浏览器类型")
  private String browser;

  /** IP地址 */
  @ApiModelProperty(value = "IP地址")
  private String ip;

  /** 操作位置 */
  @ApiModelProperty(value = "操作位置")
  private String location;

  /** 物理地址 */
  @ApiModelProperty(value = "物理地址")
  private String mac;

  /** 详细描述 */
  @ApiModelProperty(value = "详细描述")
  private String description;

  /** 请求参数 */
  @ApiModelProperty(value = "请求参数")
  private String requestData;

  /** 请求地址 */
  @ApiModelProperty(value = "请求地址")
  private String requestUrl;

  /** 响应结果 */
  @ApiModelProperty(value = "响应结果")
  private String responseData;

  /** 类名 */
  @ApiModelProperty(value = "类名")
  private String className;

  /** 方法名 */
  @ApiModelProperty(value = "方法名")
  private String methodName;

  /** 开始时间 */
  @ApiModelProperty(value = "开始时间")
  private Date startTime;

  /** 结束时间 */
  @ApiModelProperty(value = "结束时间")
  private Date endTime;

  /** 耗时,秒 */
  @ApiModelProperty(value = "耗时,秒")
  private Long excuteTime;

  /** 创建人 */
  @ApiModelProperty(value = "创建人")
  private String creator;

  /** 创建人ID */
  @ApiModelProperty(value = "创建人ID")
  private String creatorId;

  /** 创建时间 */
  @ApiModelProperty(value = "创建时间")
  private Date createdTime;

  /** 最近操作人 */
  @ApiModelProperty(value = "最近操作人")
  private String lastOperator;

  /** 最后操作人ID */
  @ApiModelProperty(value = "最后操作人ID")
  private String lastOperatorId;

  /** 更新时间 */
  @ApiModelProperty(value = "更新时间")
  private Date updateTime;
}
