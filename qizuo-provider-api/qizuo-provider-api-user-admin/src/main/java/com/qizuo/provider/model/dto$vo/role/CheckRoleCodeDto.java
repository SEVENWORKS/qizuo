/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/** The class Check role code dto. */
@Data
@ApiModel
public class CheckRoleCodeDto implements Serializable {
  private static final long serialVersionUID = 6369434659441735160L;

  @ApiModelProperty(value = "roleId")
  private Long roleId;

  @ApiModelProperty(value = "roleCode")
  private String roleCode;
}
