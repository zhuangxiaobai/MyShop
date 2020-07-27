package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description="查询购物车列表的时候返回给前端的信息")
public class ShopcartVo {


    @ApiModelProperty(value = "品种")
    private String pingZhong;


    @ApiModelProperty(value = "品名")
    private String attrName;


    @ApiModelProperty(value = "规格")
    private String specifications;

    @ApiModelProperty(value = "材质")
    private String material;

    @ApiModelProperty(value = "仓库名")
    private String  wareName;


    @ApiModelProperty(value = "购物车记录id")
    private Short shopcartId;


    @ApiModelProperty(value = "商品金额")
    private BigDecimal money;

    @ApiModelProperty(value = "是否货还够")
    private Boolean  haveGoods;

    @ApiModelProperty(value = "商品信息")
    private Goods goods;

}
