package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MobileSendMessageParam {


    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String mobile;


    @ApiModelProperty(value = "验证码用途分类 1为注册 2为重置密码，字符串形式")
    @NotBlank(message = "验证码类型不能为空")
    private String type;

}
