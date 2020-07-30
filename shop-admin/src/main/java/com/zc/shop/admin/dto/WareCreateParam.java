package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WareCreateParam {



    @ApiModelProperty(value = "仓库名称")
    private String title;

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private Integer phone;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地址")
    private String address;


    @ApiModelProperty(value = "供应商id,传当前用户id")
    private Integer supplierId;

    @ApiModelProperty(value = "仓库介绍")
    private String content;
}
