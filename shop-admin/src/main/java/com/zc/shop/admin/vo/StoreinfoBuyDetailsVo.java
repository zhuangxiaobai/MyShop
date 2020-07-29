package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Storeinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class StoreinfoBuyDetailsVo {



    @ApiModelProperty(value = "当前店铺包含的所有商品对象的信息")
    private  List<GoodsAllInfoVo> GoodsAllInfoVoList;



    @ApiModelProperty(value = "数据总条数")
    private int total;






}
