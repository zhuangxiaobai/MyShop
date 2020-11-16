package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreinfoCreateParam {

    @ApiModelProperty(value = "店铺名称")
    private String title;

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private Integer phone;

    @ApiModelProperty(value = "经营范围，主营")
    private String business;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地址，不用")
    private String address;

    @ApiModelProperty(value = "logo")
    private String logo;



}
