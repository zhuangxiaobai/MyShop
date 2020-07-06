package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsersLoginParam {

    @ApiModelProperty(value = "登录用户名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;
}
