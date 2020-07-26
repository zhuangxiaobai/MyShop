package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.service.OrderService;
import com.zc.shop.admin.vo.OrderGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


      @Autowired
      private OrderExtMapper orderExtMapper;


      @Override
      public List<OrderGoodsVo> mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId) {

           //分页查询处理
            Integer startPage = orderSellSelectParam.getStartPage();
            Integer pageSize = orderSellSelectParam.getPageSize();
            Integer  start = (startPage-1)*pageSize +1;
            orderSellSelectParam.setStartPage(start);
            orderSellSelectParam.setSupplierId(userId);

           List<OrderGoodsVo> orderGoodsVo  = orderExtMapper.selectMySellOrder(orderSellSelectParam);


            return orderGoodsVo;
      }
}
