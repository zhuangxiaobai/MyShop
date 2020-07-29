package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.GoodsCreateParam;
import com.zc.shop.admin.dto.GoodsSelectParam;
import com.zc.shop.admin.dto.StoreinfoListParam;

import java.util.Map;

public interface GoodsService {
    int create(GoodsCreateParam goodsCreateParam, Integer userId);

    Map goodslistIndex(GoodsSelectParam goodsSelectParam);
}
