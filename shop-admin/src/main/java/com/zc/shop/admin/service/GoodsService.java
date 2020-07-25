package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.GoodsCreateParam;

public interface GoodsService {
    int create(GoodsCreateParam goodsCreateParam, Integer userId);
}
