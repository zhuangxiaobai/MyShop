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
        //获取品种，品种是写死的
         String pingZhong = goodsCreateParam.getAttrCatName();

         //获取品名，规格，材质
        attribute.setAttrName(goodsCreateParam.getAttrName());
        attribute.setAttrName(goodsCreateParam.getSpecifications());
        attribute.setAttrName(goodsCreateParam.getMaterial());

        //去查询Attribute表看看存不存在品名，规格，材质相同的信息，不存在就添加，存在就不管







        return 0;
    }
}
