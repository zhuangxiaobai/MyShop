package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class GoodsUpdateParam {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品重量，单个")
    private BigDecimal goodsWeight;

    @ApiModelProperty(value = "可售数量")
    private Short goodsNumber;

    @ApiModelProperty(value = "本网售价")
    private BigDecimal shopPrice;


    @ApiModelProperty(value = "备注")
    private String remark;


}
