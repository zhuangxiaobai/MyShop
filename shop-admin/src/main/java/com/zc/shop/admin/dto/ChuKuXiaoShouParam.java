package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChuKuXiaoShouParam {

    @ApiModelProperty(value = "品名")
    private String name;

    @ApiModelProperty(value = "规格")
    private String guige;

    @ApiModelProperty(value = "材质")
    private String caizhi;

    @ApiModelProperty(value = "钢厂")
    private String factory;


    @ApiModelProperty(value = "仓库")
    private String ware;

    @ApiModelProperty(value = "销售单订单号")
    private String orderCode;

    @ApiModelProperty(value = "查询时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;

    @ApiModelProperty(value = "查询时间结束")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;


    @ApiModelProperty(value = "供应商ID，不需要传,后台自动赋值")
    private Integer supplierId;




}
