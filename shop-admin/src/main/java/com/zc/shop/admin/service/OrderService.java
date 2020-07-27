package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.dto.ShopcartParam;
import com.zc.shop.admin.vo.OrdersAllVo;

import java.util.List;

public interface OrderService {
    List<OrdersAllVo> mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId);

    int create(List<ShopcartParam> shopcartList);
}
