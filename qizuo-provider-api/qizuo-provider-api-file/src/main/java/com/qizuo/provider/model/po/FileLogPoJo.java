package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** @Author: fangl @Description: 菜单 @Date: 14:20 2018/10/29 */
@Data
@ApiModel
public class FileLogPoJo extends BasePoJo {
  /** 上传路径 */
  @ApiModelProperty(value = "上传路径")
  private String resourceName;
  /** 上传名称 */
  @ApiModelProperty(value = "上传名称")
  private String name;
  /** 日志当前操作结果 */
  @ApiModelProperty(value = "日志当前操作结果")
  private String uploadResult;
}
