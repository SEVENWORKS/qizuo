/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.model.mybatis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qizuo.base.model.auth.UserDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * mybatis Base entity.
 */
@Data
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 2393269568666085258L;

	//映射id
	@Id
	//主键自增的方式
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 创建人ID
	 */
	//映射字段
	@Column(name = "creator_id")
	private String creatorId;

	/**
	 * 创建时间
	 */
	@Column(name = "created_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;

	/**
	 * 最近操作人
	 */
	@Column(name = "last_operator")
	private String lastOperator;

	/**
	 * 最后操作人ID
	 */
	@Column(name = "last_operator_id")
	private String lastOperatorId;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 页数
	 */
	//该注解表示这个属性是临时使用，不会被存储到数据库或者序列化
	@Transient
	private Integer pageNum;

	/**
	 * 每页大小
	 */
	@Transient
	private Integer pageSize;

	/**
	 * 排序
	 */
	@Transient
	private String orderBy;

	/**
	 * 更新用户信息
	 */
	@Transient
	//json序列化或者反序列化时候忽略某些属性
	@JsonIgnore
	public void setUpdateInfo(UserDto user) {
		if (isNew()) {
			this.creatorId = (this.lastOperatorId = user.getUserId());
			this.creator = user.getUserName();
			this.createdTime = (this.updateTime = new Date());
		}
		this.lastOperatorId = user.getUserId();
		this.lastOperator = user.getUserName() == null ? user.getLoginName() : user.getUserName();
		this.updateTime = new Date();
	}

	/**
	 * 判断是否更新
	 */
	@Transient
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}
}
