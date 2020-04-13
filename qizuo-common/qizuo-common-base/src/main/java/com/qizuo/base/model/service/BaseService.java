/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.service;

import com.qizuo.base.model.base.BasePoJo;
import com.qizuo.base.model.dao.BaseDao;
import com.qizuo.base.model.page.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 基本service层
 * @Date: 15:17 2018/10/30
 */
@Transactional(rollbackFor = Exception.class)
public class BaseService<D extends BaseDao<P>, P extends BasePoJo> {

    @Autowired
    protected D dao;

    /**
     * 增加(所有增加i开头)
     */
    public int insert(P p) {
        //添加插入基本信息
        p.preIDo();
        return dao.insert(p);
    }

    /**
     * 更新(所有更新u开头)
     */
    public int update(P p) {
        //添加更新基本信息
        p.preUDo();
        return dao.update(p);
    }

    /**
     * 删除(所有删除d开头)
     */
    public int delete(P p) {
        return dao.delete(p);
    }

    /**
     * 筛选单个(所有筛选q开头)
     */
    public P query(P p) {
        return dao.query(p);
    }

    /**
     * 筛选多个
     */
    public List<P> qList(P p) {
        return dao.qList(p);
    }

    /**
     * 查询分页(本框架分页以pageQZ名称进行拦截)
     */
    public PageDto<P> qPageQZ(PageDto<P> page) {
        page.setEntitys(dao.qPageQZ(page));
        return page;
    }

    /** ******************************以下常用********************************************** */
    /**
     * 更新状态
     */
    public boolean uStatus(P p) {
        return dao.uStatus(p);
    }
}
