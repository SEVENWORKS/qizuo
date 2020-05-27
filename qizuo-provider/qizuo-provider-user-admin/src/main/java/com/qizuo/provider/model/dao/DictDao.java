package com.qizuo.provider.model.dao;

import com.qizuo.base.model.base.DictPoJo;
import com.qizuo.base.model.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @Author: fangl
 * @Description: 字典主表
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface DictDao extends BaseDao<DictPoJo> {
    /**
     * @author: fangl
     * @description: 更新状态
     * @date: 10:40 2019/2/12
     */
    @Override
    boolean uStatus(DictPoJo dictPoJo);
}
