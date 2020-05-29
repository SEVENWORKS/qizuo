package com.qizuo.provider.model.dao;


import com.qizuo.base.model.dao.BaseDao;
import com.qizuo.provider.model.po.UserPoJo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: fangl
 * @Description: 用户
 * @Date: 11:42 2019/1/1
 */
@Mapper
@Repository
public interface UserDao extends BaseDao<UserPoJo> {
    /**
     * @author: fangl
     * @description: 查询单个用户所有信息
     * @date: 10:28 2019/1/14
     */
    UserPoJo qUserAllMsg(UserPoJo userPoJo);
}
