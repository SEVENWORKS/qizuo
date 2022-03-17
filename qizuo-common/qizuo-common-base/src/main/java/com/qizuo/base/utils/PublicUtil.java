/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.utils;

import com.qizuo.base.model.auth.UserDto;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.Thread.ThreadLocalMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 常用工具.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublicUtil {
    //判断是否是特殊url
    public static Boolean isSpeUri(String uri) {
        if (uri.contains(GlobalConstant.Url$Path.TokenInterceptor_AUTH_PATH)
                || uri.contains(GlobalConstant.Url$Path.TokenInterceptor_SECURITY_PATH0)
                || uri.contains(GlobalConstant.Url$Path.TokenInterceptor_SECURITY_PATH3)){
            return true;
        }
        return false;
    }
}
