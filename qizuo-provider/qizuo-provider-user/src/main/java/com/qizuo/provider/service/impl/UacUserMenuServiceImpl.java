package com.qizuo.provider.service.impl;


import com.qizuo.core.support.BaseService;
import com.qizuo.provider.model.domain.UacUserMenu;
import com.qizuo.provider.service.UacUserMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The class Uac user menu service.
 *
 * @author paascloud.net@gmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UacUserMenuServiceImpl extends BaseService<UacUserMenu> implements UacUserMenuService {
}
