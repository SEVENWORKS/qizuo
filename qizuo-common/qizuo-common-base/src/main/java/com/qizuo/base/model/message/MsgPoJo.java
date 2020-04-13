/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.model.message;

import com.qizuo.base.model.base.BasePoJo;
import lombok.Data;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 消息
 * @Date: 14:20 2018/10/29
 */
@Data
public class MsgPoJo extends BasePoJo {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息跳转url
     */
    private String jumpUrl;
    /**
     * 类型
     */
    private String type;
    /**
     * 发送所有关联
     */
    private String sendTypeId;
    private String sendTypeNm;
    /**
     * 发送单个关联
     */
    private String sendUserId;
    private String sendUserNm;
    /**
     * 发送多个关联
     */
    private String sendUserIds;
    private List<MsgPoJo> msgPoJos;
    /**
     * 是否已读
     */
    private String isRead;
}
