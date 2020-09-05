package com.qizuo.provider.service.dao;

import com.qizuo.base.model.dao.BaseDao;
import com.qizuo.provider.model.po.RolePoJo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/** @Author: fangl @Description: 角色 @Date: 11:42 2019/1/1 */
@Mapper
@Repository
public interface RoleDao extends BaseDao<RolePoJo> {}
