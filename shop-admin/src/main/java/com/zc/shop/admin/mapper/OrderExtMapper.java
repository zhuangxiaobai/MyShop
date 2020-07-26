package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.vo.OrderGoodsVo;
import com.zc.shop.mbg.mapper.OrderMapper;

import java.util.List;

public interface OrderExtMapper extends OrderMapper {

    List<OrderGoodsVo> selectMySellOrder(OrderSellSelectParam orderSellSelectParam);
}