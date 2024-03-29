package com.qizuo.provider.service;

import com.qizuo.base.model.service.BaseService;
import com.qizuo.provider.model.po.PayPoJo;
import com.qizuo.provider.service.dao.PayDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @Author: fangl @Description: 菜单 @Date: 12:13 2019/1/1 */
@Service
@Transactional(rollbackFor = Exception.class)
// 表明此类上所有方法上的事务都是CGLib方式代理的
// 原因：springboot的事务默认是使用jdk的动态代理，即基于接口，也就是引用service上不能直接依赖实现类，如果非要这样使用，就要加上这一句
// @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PayService extends BaseService<PayDao, PayPoJo> {}
