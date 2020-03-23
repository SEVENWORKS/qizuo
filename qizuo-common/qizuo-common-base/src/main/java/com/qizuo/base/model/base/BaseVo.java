/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * Base vo.
 */
@ApiModel
@Data
public class BaseVo implements Serializable {
	private static final long serialVersionUID = -1695850022460957581L;

	/**
	 * 基本id
	 */
	private String id;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 创建人ID
	 */
	private String creatorId;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;

	/**
	 * 最近操作人
	 */
	private String lastOperator;

	/**
	 * 最后操作人ID
	 */
	private String lastOperatorId;

	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
}
