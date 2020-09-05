/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/** The class Refresh token dto. */
@Data
@ApiModel(value = "RefreshTokenDto")
public class RefreshTokenDto {
  @NotBlank
  @ApiModelProperty(value = "refreshToken")
  private String refreshToken;

  @NotBlank
  @ApiModelProperty(value = "accessToken")
  private String accessToken;
}
