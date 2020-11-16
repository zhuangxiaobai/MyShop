package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsSelectParam {

    @ApiModelProperty(value = "品种")
   private List<String>  pingZhongList;

   @ApiModelProperty(value = "品名")
   private List<String>  attrNameList;
    @ApiModelProperty(value = "材质")
    private List<String>  materialList;
    @ApiModelProperty(value = "规格")
    private List<String>  specificationsList;
    @ApiModelProperty(value = "厂家(地区)")
    private List<String>  attr1All;
    @ApiModelProperty(value = "交货地(提货地点)")
    private List<String>  attr2All;

    @ApiModelProperty(value = "仓库名")
    private String wareName;




    @ApiModelProperty(value = "排序方式 null为默认(创建时间),1价格正序，2价格倒叙")
    private Integer orderBy;
    @ApiModelProperty(value = "最低价")
    private BigDecimal priceLow;
    @ApiModelProperty(value = "最高价")
    private BigDecimal priceHigh;

    @ApiModelProperty(value = "分页对象")
    private PageParam pageParam;




}
