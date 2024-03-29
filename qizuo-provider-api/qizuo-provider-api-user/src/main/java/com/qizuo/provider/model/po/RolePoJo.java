package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** @Author: fangl @Description: 角色 @Date: 14:20 2018/10/29 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class RolePoJo extends BasePoJo {
  /** 名称 */
  @ApiModelProperty(value = "名称")
  private String name;
  /** 资源拼接串 */
  @ApiModelProperty(value = "资源拼接串")
  private String menuIds;
  /** 数据操作权限拼接串 */
  @ApiModelProperty(value = "数据操作权限拼接串")
  private String dataScopeCds;

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

  public String getMenuIds() {
    return menuIds;
  }

  public void setMenuIds(String menuIds) {
    this.menuIds = menuIds;
  }
}
