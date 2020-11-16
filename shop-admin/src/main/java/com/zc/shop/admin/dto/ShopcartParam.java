package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShopcartParam {


    private Short id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;


    @ApiModelProperty(value = "商品信息")
    private String goods;

    @ApiModelProperty(value = "供货商id")
    private Short suppliersId;

    @ApiModelProperty(value = "订单备注")
    private String remark;



}
