package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSellListParam {

    @ApiModelProperty(value = "卖家id，不要传")
    private Integer suppliersId;



    @ApiModelProperty(value = "品名")
    private String goodsName;
    @ApiModelProperty(value = "规格")
    private String guige;
    @ApiModelProperty(value = "材质")
    private String  caizhi;

    @ApiModelProperty(value = "厂家(地区,钢厂)")
    private String  factory;

    @ApiModelProperty(value = "仓库名")
    private String warehouse;

    @ApiModelProperty(value = "0上架，1下架，去掉此字段默认查所有")
    private Integer isDelete;


    @ApiModelProperty(value = "分页对象")
    private PageParam pageParam;
}
