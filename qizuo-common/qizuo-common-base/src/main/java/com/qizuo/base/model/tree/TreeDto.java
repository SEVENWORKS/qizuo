/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.tree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** The class Tree node. */
@Data
@ApiModel
public class TreeDto<T extends TreeDto, ID extends Serializable> {
  /** 节点编码 */
  @ApiModelProperty(value = "节点编码")
  private String nodeCode;
  /** 节点名称 */
  @ApiModelProperty(value = "节点名称")
  private String nodeName;
  /** 节点事件 */
  @ApiModelProperty(value = "节点事件")
  private String nodeEvent;
  /** ID */
  @ApiModelProperty(value = "ID")
  private Long id;
  /** 父ID */
  @ApiModelProperty(value = "父ID")
  private Long pid;
  /** 孩子节点信息 */
  @ApiModelProperty(value = "孩子节点信息")
  private List<T> children;
  /** 是否含有子节点 */
  @ApiModelProperty(value = "是否含有子节点")
  private boolean hasChild = false;
}
