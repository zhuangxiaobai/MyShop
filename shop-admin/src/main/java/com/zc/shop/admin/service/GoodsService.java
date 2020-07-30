package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;
import com.zc.shop.common.api.CommonResult;

import java.util.Map;

public interface GoodsService {
    int create(GoodsCreateParam goodsCreateParam, Integer userId);

    Map goodslistIndex(GoodsSelectParam goodsSelectParam);

    Map goodsSelllist(GoodsSellListParam goodsSellListParam);

    int updateIsOnSale(GoodsUpdateOnSaleParam goodsUpdateOnSaleParam);

    Map selectGoodsById(Integer goodsId);

    int updateGoodsInfo(GoodsUpdateParam goodsUpdateParam);
}
