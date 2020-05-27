package com.qizuo.provider.model.po;


import com.qizuo.base.model.base.BasePoJo;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 用户
 * @Date: 14:20 2018/10/29
 */
public class UserPoJo extends BasePoJo {
    /**
     * 登录名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 权限拼接串
     */
    private String roleIds;
    /**
     * 名称
     */
    private String name;
    /**
     * 性别
     */
    private String sexCd;
    private String sexNm;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮件
     */
    private String email;
    /**
     * 照片
     */
    private String photo;
    /**
     * 住址
     */
    private String address;
    /**
     * 登录时间
     */
    private String loginDate;
    /**
     * KEY(第三方)
     */
    private String outMutualKey;

    /** 其它 */
    /**
     * 权限集合
     */
    private List<RolePoJo> rolePoJos;
    /**
     * 菜单集合
     */
    private List<MenuPoJo> menuPoJos;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public String getSexNm() {
        return sexNm;
    }

    public void setSexNm(String sexNm) {
        this.sexNm = sexNm;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<RolePoJo> getRolePoJos() {
        return rolePoJos;
    }

    public void setRolePoJos(List<RolePoJo> rolePoJos) {
        this.rolePoJos = rolePoJos;
    }

    public List<MenuPoJo> getMenuPoJos() {
        return menuPoJos;
    }

    public void setMenuPoJos(List<MenuPoJo> menuPoJos) {
        this.menuPoJos = menuPoJos;
    }

    public String getOutMutualKey() {
        return outMutualKey;
    }

    public void setOutMutualKey(String outMutualKey) {
        this.outMutualKey = outMutualKey;
    }
}
