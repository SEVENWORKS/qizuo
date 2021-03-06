/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qizuo.base.model.auth.UserDto;
import com.qizuo.base.utils.UserUtil;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.common.IDUtil;
import com.qizuo.util.http.HttpBaseUtil;
import com.qizuo.util.spring.SpringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Transient;

/** @Author: fangl @Description: 基本实体类 @Date: 10:25 2018/10/30 */
@Data
@ApiModel
public class BasePoJo extends KvDto {
  /** id */
  // 映射id
  // 主键自增的方式
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id")
  private String baseId;
  /** 创建ip */
  // 映射字段
  @Column(name = "")
  @ApiModelProperty(value = "创建ip")
  private String baseCreateIp;
  /** 更新ip */
  @ApiModelProperty(value = "更新ip")
  private String baseUpdateIp;
  /** 创建人 */
  @ApiModelProperty(value = "创建人")
  private String baseCreateUserId;

  @ApiModelProperty(value = "创建人 name")
  private String baseCreateUserNm;
  /** 创建时间 */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty(value = "创建时间")
  private String baseCreateTime;
  /** 更新人 */
  @ApiModelProperty(value = "更新人")
  private String baseUpdateUserId;

  @ApiModelProperty(value = "更新人 name")
  private String baseUpdateUserNm;
  /** 更新时间 */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty(value = "更新时间")
  private String baseUpdateTime;
  /** 状态 1有效 0无效 */
  @ApiModelProperty(value = "状态 1有效 0无效")
  private String baseStatus;
  /** 备注 数据备注 */
  @ApiModelProperty(value = "备注")
  private String baseRemarks;
  /** 排序 */
  @ApiModelProperty(value = "排序")
  private String orderBy;

  /** 其它 */
  /** 创建人信息 */
  // 该注解表示这个属性是临时使用，不会被存储到数据库或者序列化
  @Transient
  // json序列化或者反序列化时候忽略某些属性
  @JsonIgnore

  /** 插入前动作 */
  public void preIDo() {
    // 人员
    UserDto userDto = UserUtil.qUser();
    this.baseCreateUserId = userDto.getBaseId();
    // 状态
    this.baseStatus = GlobalConstant.STATUS_YES;
    // ip
    String ip = HttpBaseUtil.getIpAddress(SpringUtils.qHttpServletRequest());
    this.baseCreateIp = ip;
    // id
    this.baseId = IDUtil.nextId();
  }

  /** 更新前动作 */
  public void preUDo() {
    // 人员
    UserDto userDto = UserUtil.qUser();
    this.baseUpdateUserId = userDto.getBaseId();
    // ip
    String ip = HttpBaseUtil.getIpAddress(SpringUtils.qHttpServletRequest());
    this.baseUpdateIp = ip;
  }
}
