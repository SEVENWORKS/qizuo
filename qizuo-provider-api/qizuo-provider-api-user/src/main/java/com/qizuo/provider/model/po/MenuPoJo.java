package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.util.List;

/** @Author: fangl @Description: 菜单 @Date: 14:20 2018/10/29 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MenuPoJo extends BasePoJo {
  /** 名称 */
  @ApiModelProperty(value = "名称")
  //对象的参数校验，如下方式，后面message取的是ValidationMessages.properties中数据
  @Size(min = 1,max = 100,message = "{menu.name.size}")
  private String name;
  /** 跳转路径 */
  @ApiModelProperty(value = "跳转路径")
  private String url;
  /** 父ID */
  @ApiModelProperty(value = "父ID")
  private String parentId;
  /** 主题 */
  @ApiModelProperty(value = "主题")
  private String theme;
  /** 子菜单集合 */
  @ApiModelProperty(value = "子菜单集合")
  private List<MenuPoJo> menuPoJos;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public List<MenuPoJo> getMenuPoJos() {
    return menuPoJos;
  }

  public void setMenuPoJos(List<MenuPoJo> menuPoJos) {
    this.menuPoJos = menuPoJos;
  }

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }
}
