package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegiinvoiceSearchParam {


    private String companyName;


    private String orderCode;


    @ApiModelProperty(value = "需要传入当前卖方的id")
    private Integer supplierId;

    @ApiModelProperty(value = "查询的服务单状态（此处为待开发票为6）")
    private Integer status;


    @ApiModelProperty(value = "起始页")
    private Integer startPage;

    @ApiModelProperty(value = "每页容量")
    private Integer PageSize;



}
