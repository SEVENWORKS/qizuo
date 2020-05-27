package com.qizuo.provider.model.dao;

import com.qizuo.base.model.dao.BaseDao;
import com.qizuo.provider.model.po.MenuPoJo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 菜单
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface MenuDao extends BaseDao<MenuPoJo> {

    /**
     * @author: fangl
     * @description: 查询菜单递归列表
     * @date: 17:44 2019/1/9
     */
    List<MenuPoJo> qEachList(MenuPoJo menuPoJo);


}
