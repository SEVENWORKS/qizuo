/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** Kv dto. */
@Data
@ApiModel
public class KvDto<K, V> implements Serializable {

  private static final long serialVersionUID = -7712636075929650779L;

  public KvDto() {}

  public KvDto(K key, V value) {
    this.key = key;
    this.value = value;
  }

  /** key */
  @ApiModelProperty(value = "key")
  private K key;
  /** value */
  @ApiModelProperty(value = "value")
  private V value;
  /** keys */
  @ApiModelProperty(value = "keys")
  private List<K> keys;
  /** values */
  @ApiModelProperty(value = "values")
  private List<V> values;
  /** map */
  @ApiModelProperty(value = "map")
  private Map<K, V> params;
}
