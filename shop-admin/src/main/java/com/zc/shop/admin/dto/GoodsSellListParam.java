package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSellListParam {


    @ApiModelProperty(value = "卖家id")
    private Integer SupplierId;



    @ApiModelProperty(value = "品名")
    private String attrName;
    @ApiModelProperty(value = "材质")
    private String  material;
    @ApiModelProperty(value = "规格")
    private String specifications;
    @ApiModelProperty(value = "厂家(地区,钢厂)")
    private String  attr1;

    @ApiModelProperty(value = "仓库名")
    private String wareName;

    @ApiModelProperty(value = "null全部，1上架，2下架")
    private Integer isOnSale;


    @ApiModelProperty(value = "分页对象")
    private PageParam pageParam;
}
