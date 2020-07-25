package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.GoodsCreateParam;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.mbg.po.Attribute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(GoodsCreateParam goodsCreateParam, Integer userId) {

       //获取品种,品名,规格,材质 1.已经在attribute表中存在不管 2.不存在，添加
        Attribute  attribute = new Attribute();
        //获取品种
        attribute.setAttrName(goodsCreateParam.getAttrCatName());
        //







        return 0;
    }
}
