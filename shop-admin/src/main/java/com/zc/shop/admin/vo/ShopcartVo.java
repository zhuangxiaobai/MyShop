package com.zc.shop.admin.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Shopcart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description="查询购物车列表的时候返回给前端的信息")
public class ShopcartVo {

    @ApiModelProperty(value = "商品信息")
    private Goods goods;


    @ApiModelProperty(value = "商品金额")
    private BigDecimal money;

    @ApiModelProperty(value = "是否货还够")
    private Boolean  haveGoods  ;
}
