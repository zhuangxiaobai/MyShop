package com.zc.shop.admin;

import com.zc.shop.admin.mapper.GoodsExtMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZcTest {

    @Autowired
    private GoodsExtMapper goodsExtMapper;


    @Test
    void test() {

        Short buyNum = 2;
        Integer goodsId = 1;

        int updateNumSuccess = goodsExtMapper.updateGoodsNumPlus(goodsId, buyNum);



    }





}
