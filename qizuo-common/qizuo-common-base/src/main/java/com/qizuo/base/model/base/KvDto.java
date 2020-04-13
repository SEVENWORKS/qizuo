/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Kv dto.
 */
@Data
public class KvDto<K, V> implements Serializable {

	private static final long serialVersionUID = -7712636075929650779L;

	public KvDto() {

	}

	public KvDto(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * key
	 */
	private K key;
	/**
	 * value
	 */
	private V value;
	/**
	 * keys
	 */
	private List<K> keys;
	/**
	 * values
	 */
	private List<V> values;
	/**
	 * map
	 */
	private Map<K, V> params;

}
