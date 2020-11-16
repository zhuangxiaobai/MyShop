package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.ShopcartExtMapper;
import com.zc.shop.admin.mapper.WareExtMapper;
import com.zc.shop.admin.service.ShopcartService;
import com.zc.shop.admin.vo.ShopcartGaiVo;
import com.zc.shop.admin.vo.ShopcartVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Attribute;
import com.zc.shop.mbg.po.Shopcart;
import com.zc.shop.mbg.po.Ware;
import com.zc.shop.mbg.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopcartServiceImpl implements ShopcartService {

    @Autowired
    private ShopcartExtMapper shopcartExtMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;

    @Autowired
    private WareExtMapper wareExtMapper;

    @Autowired
    private AttributeExtMapper attributeExtMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addShopcart(Shopcart shopcart) {

        //包含了goodid和商品数量
        String goodsParam = shopcart.getGoods();
        JSONObject goodsJson = JSONObject.parseObject(goodsParam);
        Integer goodId = (Integer) goodsJson.get("good_id");
        Integer num = (Integer) goodsJson.get("num");

        //获取商品
        Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

        //查看余量是否够
        //Short goodsNumber = goods.getGoodsNumber();
        //不够了
//        if((goodsNumber - num)<0 ){
//            throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
//        }
        shopcart.setCreatedAt(LocalDateTime.now());
        shopcart.setUpdatedAt(LocalDateTime.now());


       //添加到shopcart表
        return shopcartExtMapper.insertSelective(shopcart);

    }

    @Override
    public List<ShopcartGaiVo> shopcartList(Integer userId) {

        List<ShopcartGaiVo> shopcartGaiVoList = new ArrayList<>();
        //通过userId获取所有购物车对象
        List<Shopcart> shopcartList = shopcartExtMapper.selectByUserId(userId);


        for (Shopcart shopcart : shopcartList) {
            ShopcartGaiVo shopcartGaiVo = new ShopcartGaiVo();
            JSONObject goodsJson = JSONObject.parseObject(shopcart.getGoods());
            Integer goodId = (Integer) goodsJson.get("good_id");
           // Integer num = (Integer) goodsJson.get("num");

            //获取商品
            Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

            //查看余量是否够
            //   Short goodsNumber = goods.getGoodsNumber();
            //不够了
//            if((goodsNumber - num)<0 ){
//                shopcartVo.setHaveGoods(false);
//            }else {
            //够
            // }


            Integer num = (Integer) goodsJson.get("num");

            shopcartGaiVo.setGoods(goods);
            shopcartGaiVo.setShopcart(shopcart);
            shopcartGaiVo.setNum(num);
            shopcartGaiVoList.add(shopcartGaiVo);
        }
        return shopcartGaiVoList;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateShopcart(Shopcart shopcart) {

        //包含了goodid和商品数量
        String goodsParam = shopcart.getGoods();
        JSONObject goodsJson = JSONObject.parseObject(goodsParam);
        Integer goodId = (Integer) goodsJson.get("good_id");
        Integer buynum = (Integer) goodsJson.get("num");

        //获取商品
        Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

        //查看余量是否够
        Short remainNumber = goods.getRemainNumber();
        //不够了
        if((remainNumber - buynum)<0 ){
            throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
        }

        shopcart.setUpdatedAt(LocalDateTime.now());



        //够，修改shopcart表记录
        return shopcartExtMapper.updateByPrimaryKeySelective(shopcart);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteShopcart(List<Short> shopcartIds) {

       int count = 0;
       for(Short shopcartId:shopcartIds){

           int i = shopcartExtMapper.deleteByPrimaryKey(shopcartId);
           if(i>0){
               count ++;
           }
       }

       if(count == shopcartIds.size()){

           return count;
       }else{

           throw  new  BusinessException(ResultCode.FAILED);
       }


    }
}
