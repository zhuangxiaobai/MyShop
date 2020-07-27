package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.OrderBuySelectParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.vo.OrdersAllVo;
import com.zc.shop.mbg.mapper.OrderMapper;

import java.util.List;

public interface OrderExtMapper extends OrderMapper {

    List<OrdersAllVo> selectMySellOrder(OrderSellSelectParam orderSellSelectParam);

    List<OrdersAllVo> selectMyBuyOrder(OrderBuySelectParam orderBuySelectParam);
}