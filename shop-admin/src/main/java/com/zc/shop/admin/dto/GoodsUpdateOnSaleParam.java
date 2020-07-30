package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsUpdateOnSaleParam {

    @ApiModelProperty(value = "卖家id,不要了")
    private Integer supplierId;

    @ApiModelProperty(value = "商品id 必须")
    private Integer goodsId;

    @ApiModelProperty(value = "是否上架 1上架 0下架 必须")
    private Integer IsOnsale;

}
