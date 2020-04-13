/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.token;


import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * The class Token main query dto.
 */
@Data
@ApiModel
public class TokenMainQueryDto {
	private static final long serialVersionUID = -4003383211817581110L;
	private String loginName;

	private String userName;

	private Integer status;
}
