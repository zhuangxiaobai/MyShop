package com.zc.shop.admin.dto;

import com.zc.shop.admin.vo.ShopcartGaiVo;
import com.zc.shop.mbg.po.Shopcart;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateParam {



    //List<Shopcart> shopcartList;

    @ApiModelProperty(value = "传入返回的购物车等对象集合")
    private  List<ShopcartGaiVo> shopcartGaiVoList;


    @ApiModelProperty(value = "卖家付款金额")
    private  BigDecimal payment;


    @ApiModelProperty(value = "保证金")
    private BigDecimal margin;



    @ApiModelProperty(value = "备注")
    private String remark;
}
