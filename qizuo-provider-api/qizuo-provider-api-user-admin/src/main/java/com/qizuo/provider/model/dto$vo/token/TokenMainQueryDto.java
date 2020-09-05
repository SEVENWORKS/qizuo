/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** The class Token main query dto. */
@Data
@ApiModel
public class TokenMainQueryDto {
  private static final long serialVersionUID = -4003383211817581110L;

  @ApiModelProperty(value = "loginName")
  private String loginName;

  @ApiModelProperty(value = "userName")
  private String userName;

  @ApiModelProperty(value = "status")
  private Integer status;
}
