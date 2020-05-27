package com.qizuo.provider.service;

import com.qizuo.base.model.base.DictItemPoJo;
import com.qizuo.base.model.base.DictPoJo;
import com.qizuo.base.model.service.BaseService;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.provider.model.dao.DictDao;
import com.qizuo.util.common.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fangl
 * @Description: 字典子表
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictService extends BaseService<DictDao, DictPoJo> {
    @Autowired
    private DictItemService dictItemService;

    /**
     * @author: fangl
     * @description: 批量插入或者更新
     * @date: 10:02 2019/2/12
     */
    public boolean iuBatch(DictPoJo dictPoJo) {
        if (StringUtils.isBlank(dictPoJo.getBaseId())) {
            //插入
            this.insert(dictPoJo);
        } else {
            //更新
            this.update(dictPoJo);
            //先删除所有从表
            DictItemPoJo dictItemPoJo = new DictItemPoJo();
            dictItemPoJo.setDictId(dictPoJo.getBaseId());
            dictItemService.delete(dictItemPoJo);
        }
        if (null != dictPoJo.getDictItemPoJos()) {
            //主键生成
            IDUtil.nextId(dictPoJo.getDictItemPoJos());
            //批量插入从表
            dictItemService.insert(dictPoJo);
        }
        return true;
    }

    /**
     * @author: fangl
     * @description: 修改字典表状态
     * @date: 10:03 2019/2/12
     */
    @Override
    public boolean uStatus(DictPoJo dictPoJo) {
        //先修改从表状态
        DictItemPoJo dictItemPoJo = new DictItemPoJo();
        dictItemPoJo.setDictId(dictPoJo.getBaseId());
        dictItemPoJo.setBaseStatus(GlobalConstant.STATUS_NO);
        dictItemService.uStatus(dictItemPoJo);
        //再修改主表状态
        dictPoJo.setBaseStatus(GlobalConstant.STATUS_NO);
        dao.uStatus(dictPoJo);
        return true;
    }

}
