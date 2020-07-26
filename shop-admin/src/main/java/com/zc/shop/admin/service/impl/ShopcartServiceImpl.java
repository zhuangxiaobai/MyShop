package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.ShopcartExtMapper;
import com.zc.shop.admin.service.ShopcartService;
import com.zc.shop.admin.vo.ShopcartVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.mapper.ShopcartMapper;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Shopcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public int addShopcart(Shopcart shopcart) {

        //包含了goodid和商品数量
        String goodsParam = shopcart.getGoods();
        JSONObject goodsJson = JSONObject.parseObject(goodsParam);
        Integer goodId = (Integer) goodsJson.get("good_id");
        Integer num = (Integer) goodsJson.get("num");

        //获取商品
        Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

        //查看余量是否够
        Short goodsNumber = goods.getGoodsNumber();
        //不够了
        if((goodsNumber - num)<0 ){
            throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
        }
        shopcart.setCreatedAt(LocalDateTime.now());
        shopcart.setUpdatedAt(LocalDateTime.now());




       //够，添加到shopcart表
        return shopcartExtMapper.insertSelective(shopcart);

    }

    @Override
    public List<ShopcartVo> shopcartList(Integer userId) {

        List<ShopcartVo> shopcartVoList = new ArrayList<>();
        //通过userId获取所有购物车对象
        List<Shopcart> shopcartList = shopcartExtMapper.selectByUserId(userId);


        for(Shopcart shopcart : shopcartList){
            ShopcartVo shopcartVo = new ShopcartVo();
            JSONObject goodsJson = JSONObject.parseObject(shopcart.getGoods());
            Integer goodId = (Integer) goodsJson.get("good_id");
            Integer num = (Integer) goodsJson.get("num");

            //获取商品
            Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

            //查看余量是否够
            Short goodsNumber = goods.getGoodsNumber();
            //不够了
            if((goodsNumber - num)<0 ){
                shopcartVo.setHaveGoods(false);
            }
            //够
            shopcartVo.setHaveGoods(true);
            BeanUtil.copyProperties(goods,shopcartVo);

            //够取出商品中的 价格 * 数量 算出金额保留两位小数
            BigDecimal shopPrice = goods.getShopPrice();
            BigDecimal money = shopPrice.multiply(new BigDecimal(num));

            shopcartVo.setMoney(money);

            shopcartVoList.add(shopcartVo);
        }



        return shopcartVoList;
    }
}
