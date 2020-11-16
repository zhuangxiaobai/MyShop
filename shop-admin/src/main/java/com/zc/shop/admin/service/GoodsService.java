package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    int create(List<GoodsCreateParam> goodsCreateParamList, Integer userId);

   Map goodslistIndex(GoodsSelectGaiParam goodsSelectGaiParam);

   Map goodsSelllist(GoodsSellListParam goodsSellListParam);

    int updateIsOnSale(GoodsUpdateOnSaleParam goodsUpdateOnSaleParam);

//    Map selectGoodsById(Integer goodsId);

   int updateGoodsInfo(GoodsUpdateParam goodsUpdateParam);
}
