/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/** @Author: fangl @Description: 分页实体类(为什么要继承list, 因为分页拦截器那边) @Date: 10:28 2018/11/19 */
@Data
@ApiModel
public class PageDto<P> /*extends ArrayList<P> */ {
  /** 页数 */
  @ApiModelProperty(value = "页数")
  private int pageNo = 1;
  /** 每页数量 */
  @ApiModelProperty(value = "每页数量")
  private int pageSize = 10;
  /** 数据总条数 */
  @ApiModelProperty(value = "数据总条数")
  private int totalCount;
  /** 数据总页数(非必要) */
  @ApiModelProperty(value = "数据总页数(非必要)")
  private int totalPage;
  /** 关联实体类 */
  @ApiModelProperty(value = "关联实体类")
  private P entity;
  /** 单页实体类集合 */
  @ApiModelProperty(value = "单页实体类集合")
  private List<P> entitys;

  public PageDto() {}

  public PageDto(int pageNo, int pageSize, int totalCount, List<P> entitys) {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
    this.totalCount = totalCount;
    this.entitys = entitys;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    /** 设置总条数的时候同时设置总页数 */
    computeTotalPage();
  }

  private void computeTotalPage() {
    if (getPageSize() > 0 && getTotalCount() > -1) {
      this.totalPage =
          (int)
              (getTotalCount() % getPageSize() == 0
                  ? getTotalCount() / getPageSize()
                  : getTotalCount() / getPageSize() + 1);
    }
  }
}
