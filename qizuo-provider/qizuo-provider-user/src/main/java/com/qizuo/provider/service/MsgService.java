package com.qizuo.provider.service;

import com.qizuo.base.model.service.BaseService;
import com.qizuo.provider.model.po.MsgPoJo;
import com.qizuo.provider.model.po.RolePoJo;
import com.qizuo.provider.service.dao.MsgDao;
import com.qizuo.provider.service.dao.RoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fangl
 * @Description: 角色
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MsgService extends BaseService<MsgDao, MsgPoJo> {

}
