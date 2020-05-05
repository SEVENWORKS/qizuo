/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.token;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The class Refresh token dto.
 */
@Data
public class RefreshTokenDto {
	@NotBlank
	private String refreshToken;
	@NotBlank
	private String accessToken;
}
