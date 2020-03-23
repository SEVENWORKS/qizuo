/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * tree
 */
@Data
public class TreeDtoBig<E, ID> implements Serializable {
	private static final long serialVersionUID = -5703964834600572016L;

	/**
	 * ID
	 */
	private ID id;

	/**
	 * 父ID
	 */
	private ID pid;

	/**
	 * 节点编码
	 */
	private String nodeCode;
	/**
	 * 节点名称
	 */
	private String nodeName;

	/**
	 * 是否含有子节点
	 */
	private boolean hasChild = false;

	/**
	 * 子节点集合
	 */
	private List<E> children;
}