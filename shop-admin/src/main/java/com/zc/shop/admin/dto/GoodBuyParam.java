package com.zc.shop.admin.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodBuyParam {


    @ApiModelProperty(value = "商品表主ID")
    private Integer id;

    @ApiModelProperty(value = "商品表主ID")
    private Integer num;


}
