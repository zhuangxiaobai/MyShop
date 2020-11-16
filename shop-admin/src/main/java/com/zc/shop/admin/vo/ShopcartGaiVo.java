package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Shopcart;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopcartGaiVo {


    @ApiModelProperty(value = "购物车对象信息")
    private Shopcart shopcart;


    @ApiModelProperty(value = "对应商品信息")
    private Goods goods;

    @ApiModelProperty(value = "购买的件数")
    private Integer num;



}
