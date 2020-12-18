package com.zc.shop.admin.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "编辑个人基本信息要传的对象")
public class UpdatePersonalParam {


    @ApiModelProperty(value = "用户id，修改时需要传入")
    private Integer id;

    @ApiModelProperty(value = "用户真实姓名")
    private String realname;

    @ApiModelProperty(value = "昵称")
    private  String nickname;


    private Integer sex;


    private String email;


    private String Introduction;


}
