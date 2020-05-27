/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.dao;

import com.qizuo.base.model.base.BasePoJo;
import com.qizuo.base.model.page.PageDto;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 基本dao层
 * @Date: 15:17 2018/10/30
 */
public interface BaseDao<P extends BasePoJo>{
    /**
     * 增加
     */
    int insert(P p);

    /**
     * 更新
     */
    int update(P p);

    /**
     * 删除
     */
    int delete(P p);

    /**
     * 筛选单个
     */
    P query(P p);

    /**
     * 筛选多个
     */
    List<P> qList(P p);

    /**
     * 查询分页
     */
    List<P> qPageQZ(PageDto<P> page);

    /** ******************************以下常用********************************************** */
    /**
     * 更新状态
     */
    boolean uStatus(P p);
}
