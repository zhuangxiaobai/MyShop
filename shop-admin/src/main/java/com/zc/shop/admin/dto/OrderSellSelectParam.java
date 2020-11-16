package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderSellSelectParam {

    @ApiModelProperty(value = "订单编号")
    private String code;

    @ApiModelProperty(value = "买家手机号(公司)")
    private String companyPhone;

    @ApiModelProperty(value = "买家单位(公司名)")
    private String companyName;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "订单状态,不传默认查所有")
    private Integer status;

    @ApiModelProperty(value = "下单时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;

    @ApiModelProperty(value = "下单时间结束")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;

    @ApiModelProperty(value = "仓库/港口名字")
    private String wareName;

    @ApiModelProperty(value = "供应商ID，不需要传,后台自动赋值")
    private Integer supplierId;



    @ApiModelProperty(value = "起始页")
    private Integer startPage;


    @ApiModelProperty(value = "每页容量")
    private Integer PageSize;










}
