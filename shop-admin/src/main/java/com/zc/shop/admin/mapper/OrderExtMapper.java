package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.OrderBuySelectParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.vo.OrdersAllVo;
import com.zc.shop.admin.vo.OrdersGaiVo;
import com.zc.shop.mbg.mapper.OrderMapper;
import com.zc.shop.mbg.po.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderExtMapper extends OrderMapper {

    List<OrdersAllVo> selectMySellOrder(OrderSellSelectParam orderSellSelectParam);

    List<OrdersAllVo> selectMyBuyOrder(OrderBuySelectParam orderBuySelectParam);

    int updateOrderRemaing(@Param("orderId") Integer orderId, @Param("tiNum") BigDecimal tiNum);

    int selectMySellOrderNumGai(OrderSellSelectParam orderSellSelectParam);

    int selectMyBuyOrderNumGai(OrderBuySelectParam orderBuySelectParam);

    List<Order> selectOrdersByGoodsId(@Param("goodsId") Integer goodsId);


   //卖家查看卖出的订单
   List<OrdersGaiVo> selectMySellOrderGai(@Param("orderCodeGaiList")List<String> orderCodeGaiList);


    //买家查看买入的订单
    List<OrdersGaiVo> selectMyBuyOrderGai(@Param("orderCodeGaiList")List<String> orderCodeGaiList);

    //后台订单首页获取各种订单
    List<Order> selectOrderBySupplierOrBuyUserId(@Param("userId")Integer userId);

    //通过订单code查询订单集合
    List<Order> selectOrderByOrderCode(String code);

    int updateOrderNumAndWeight(Long orderId, Integer remainNum, BigDecimal remainWeight);


    //获取分页的订单号
    List<String> selectMySellOrderCodeGai(OrderSellSelectParam orderSellSelectParam);

    int selectMySellOrderCodeGaiNum(OrderSellSelectParam orderSellSelectParam);

    List<String> selectMyBuyOrderCodeGai(OrderBuySelectParam orderBuySelectParam);

    int selectMyBuyOrderCodeGaiNum(OrderBuySelectParam orderBuySelectParam);
}