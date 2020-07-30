package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.OrderBuySelectParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.vo.OrdersAllVo;
import com.zc.shop.mbg.mapper.OrderMapper;
import com.zc.shop.mbg.po.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderExtMapper extends OrderMapper {

    List<OrdersAllVo> selectMySellOrder(OrderSellSelectParam orderSellSelectParam);

    List<OrdersAllVo> selectMyBuyOrder(OrderBuySelectParam orderBuySelectParam);

    int updateOrderRemaing(@Param("orderId") Integer orderId, @Param("tiNum") BigDecimal tiNum);

    int selectMySellOrderNum(OrderSellSelectParam orderSellSelectParam);

    int selectMyBuyOrderNum(OrderBuySelectParam orderBuySelectParam);

    List<Order> selectOrdersByGoodsId(@Param("goodsId") Integer goodsId);
}