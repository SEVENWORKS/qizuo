package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/** @Author: fangl @Description: 用户 @Date: 14:20 2018/10/29 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class UserPoJo extends BasePoJo {
  /** 登录名 */
  @ApiModelProperty(value = "登录名")
  private String userName;
  /** 密码 */
  @ApiModelProperty(value = "密码")
  private String passWord;
  /** 密码盐 */
  @ApiModelProperty(value = "密码盐")
  private String salt;
  /** 权限拼接串 */
  @ApiModelProperty(value = "权限拼接串")
  private String roleIds;
  /** 名称 */
  @ApiModelProperty(value = "名称")
  private String name;
  /** 性别 */
  @ApiModelProperty(value = "性别")
  private String sexCd;

  @ApiModelProperty(value = "性别名称")
  private String sexNm;
  /** 身份证 */
  @ApiModelProperty(value = "身份证")
  private String idCard;
  /** 电话 */
  @ApiModelProperty(value = "电话")
  private String phone;
  /** 邮件 */
  @ApiModelProperty(value = "邮件")
  private String email;
  /** 照片 */
  @ApiModelProperty(value = "照片")
  private String photo;
  /** 住址 */
  @ApiModelProperty(value = "住址")
  private String address;
  /** 登录时间 */
  @ApiModelProperty(value = "登录时间")
  private String loginDate;
  /** KEY(第三方) */
  @ApiModelProperty(value = "KEY(第三方)")
  private String outMutualKey;
  /** groupId */
  @ApiModelProperty(value = "groupId")
  private String groupId;
  /** groupName */
  @ApiModelProperty(value = "groupName")
  private String groupName;

  /** 其它 */
  /** 权限集合 */
  @ApiModelProperty(value = "权限集合")
  private List<RolePoJo> rolePoJos;
  /** 菜单集合 */
  @ApiModelProperty(value = "菜单集合")
  private List<MenuPoJo> menuPoJos;
}
