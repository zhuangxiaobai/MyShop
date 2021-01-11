package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;
import com.zc.shop.mbg.po.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    int create(List<GoodsCreateParam> goodsCreateParamList, Integer userId);

   Map goodslistIndex(GoodsSelectGaiParam goodsSelectGaiParam);

   Map goodsSelllist(GoodsSellListParam goodsSellListParam);

    int updateIsOnSale(GoodsUpdateOnSaleParam goodsUpdateOnSaleParam);

//    Map selectGoodsById(Integer goodsId);

   int updateGoodsInfo(GoodsUpdateParam goodsUpdateParam);



   //商品的加减,加锁方法
    /**
     * @param  goodId 商品id
     * @param jiaorjian 是＋还是-,0是加,1是减
     * @param num
     * @return 返回修改为的值
     */
    Goods updateGoodNum(int goodId, int jiaorjian, int num);



}
