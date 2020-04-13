/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.role;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Check role code dto.
 */
@Data
public class CheckRoleCodeDto implements Serializable {
	private static final long serialVersionUID = 6369434659441735160L;

	private Long roleId;
	private String roleCode;
}
