package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.OrderBuySelectParam;
import com.zc.shop.admin.dto.OrderParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.dto.ShopcartParam;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId);

    int create(List<ShopcartParam> shopcartList);

    Map myBuyOrder(OrderBuySelectParam orderBuySelectParam, Integer userId);

    int updateShopcart(OrderParam orderParam);
}
