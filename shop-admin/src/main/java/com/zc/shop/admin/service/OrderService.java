package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.vo.OrderGoodsVo;

import java.util.List;

public interface OrderService {
    List<OrderGoodsVo> mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId);
}
