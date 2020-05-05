/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.model.dto$vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Uac menu url dto.
 */
@Data
@ApiModel(value = "根据URL查询菜单Dto")
public class MenuUrlDto implements Serializable {


	private static final long serialVersionUID = 456904676277011675L;
	@ApiModelProperty(value = "系统Id", required = true)
	private String systemId;

	@ApiModelProperty(value = "用户ID", required = true)
	private Long userId;

	@ApiModelProperty(value = "url", required = true)
	private String url;
}
