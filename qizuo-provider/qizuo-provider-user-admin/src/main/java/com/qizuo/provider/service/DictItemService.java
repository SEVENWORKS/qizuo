package com.qizuo.provider.service;

import com.qizuo.base.model.base.DictItemPoJo;
import com.qizuo.base.model.base.DictPoJo;
import com.qizuo.base.model.service.BaseService;
import com.qizuo.provider.model.dao.DictItemDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fangl
 * @Description: 字典主表
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictItemService extends BaseService<DictItemDao, DictItemPoJo> {
    /**
     * @author: fangl
     * @description: 批量插入
     * @date: 10:37 2019/2/12
     */
    public boolean insert(DictPoJo dictPoJo) {
        return dao.insert(dictPoJo);
    }
}
