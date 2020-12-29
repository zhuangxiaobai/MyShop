package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserNameParam {

//    @ApiModelProperty("用户id,必须" )
//    private Integer id;

    @ApiModelProperty("用户老账号,必须用于验证" )
    private String userName;

    @ApiModelProperty("用户密码,必须用于验证" )
    private String password;

    @ApiModelProperty("需要的变更的账号(手机号)" )
    private String newUserName;



}
