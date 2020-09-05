package com.qizuo.provider.service;

import com.qizuo.base.model.service.BaseService;
import com.qizuo.provider.service.dao.MenuDao;
import com.qizuo.provider.model.po.MenuPoJo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 菜单
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService extends BaseService<MenuDao, MenuPoJo> {

    /**
     * @author: fangl
     * @description: 菜单递归查询
     * @date: 17:42 2019/1/9
     */
    public List<MenuPoJo> qEachList(MenuPoJo menuPoJo) {
        return dao.qEachList(menuPoJo);
    }
}
