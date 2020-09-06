package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import lombok.Data;

/** @Author: fangl @Description: 菜单 @Date: 14:20 2018/10/29 */
@Data
public class FilePoJo extends BasePoJo {
  /** 上传数据类型 */
  private String dataType;
  /** 关联数据id */
  private String dataId;
  /** 关联字段 */
  private String dataColumn;
  /** 上传路径 */
  private String resourceName;
  /** 上传名称 */
  private String name;
  /** 日志当前操作结果 */
  private String uploadResult;
}
