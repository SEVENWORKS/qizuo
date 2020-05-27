package com.qizuo.provider.model.po;


import com.qizuo.base.model.base.BasePoJo;

/**
 * @Author: fangl
 * @Description: 角色
 * @Date: 14:20 2018/10/29
 */
public class RolePoJo extends BasePoJo {
    /**
     * 名称
     */
    private String name;
    /**
     * 资源拼接串
     */
    private String menuIds;
    /**
     * 数据操作权限拼接串
     */
    private String dataScopeCds;
    /**
     * 首页路径
     */
    private String indexUrl;
    /**
     * 跳转路径
     */
    private String jumpUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataScopeCds() {
        return dataScopeCds;
    }

    public void setDataScopeCds(String dataScopeCds) {
        this.dataScopeCds = dataScopeCds;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
