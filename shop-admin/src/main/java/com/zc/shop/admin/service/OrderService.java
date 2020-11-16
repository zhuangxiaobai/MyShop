package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;
import com.zc.shop.mbg.po.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId);

    //int create(OrderCreateParam orderCreateParam,Integer userId);

    Map myBuyOrder(OrderBuySelectParam orderBuySelectParam, Integer userId);

    int updateShopcart(OrderParam orderParam);

    List<Order> myOrderIndex(Integer userId);

    int createOrder(List<SupplierGoodsParam> supplierGoodsParamList, Integer userId);
}
