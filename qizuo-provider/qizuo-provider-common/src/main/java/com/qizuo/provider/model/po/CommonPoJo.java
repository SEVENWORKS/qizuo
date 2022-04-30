package com.qizuo.provider.model.po;

import com.qizuo.base.model.base.BasePoJo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/** @Author: fangl @Description: 常用 @Date: 14:20 2018/10/29 */
@Data
@ApiModel
public class CommonPoJo extends BasePoJo {
    /** 内容 */
    @ApiModelProperty(value = "内容")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
