package com.zc.shop.admin;

import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.vo.OrdersGaiVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZcTest {

    @Autowired
    private GoodsExtMapper goodsExtMapper;


    @Autowired
    private OrderExtMapper orderExtMapper;


    @Test
    void test() {
        OrderSellSelectParam orderSellSelectParam = null;

        //List<OrdersGaiVo> ordersGaiVos = orderExtMapper.selectMySellOrderGai(orderSellSelectParam);
        //System.out.println(ordersGaiVos.toString());

    }





}
