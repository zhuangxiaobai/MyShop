package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUsersPasswordParam {

    @ApiModelProperty(value = "老密码")
    private String password;

    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
