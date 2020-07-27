package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.ShopcartExtMapper;
import com.zc.shop.admin.mapper.WareExtMapper;
import com.zc.shop.admin.service.ShopcartService;
import com.zc.shop.admin.vo.ShopcartVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.mapper.ShopcartMapper;
import com.zc.shop.mbg.po.Attribute;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Shopcart;
import com.zc.shop.mbg.po.Ware;
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
            }else {
                //够

                //获取仓库名称
                Short wareId = goods.getWareId().shortValue();
                Ware ware = wareExtMapper.selectByPrimaryKey(wareId);
                shopcartVo.setWareName(ware.getTitle());


                //获取品名，规格，材质
                Short catId = goods.getCatId();
                Attribute attribute = attributeExtMapper.selectByPrimaryKey(catId);
                shopcartVo.setAttrName(attribute.getAttrName());
                shopcartVo.setMaterial(attribute.getMaterial());
                shopcartVo.setSpecifications(attribute.getSpecifications());


                //获取品种
                Integer parentId = attribute.getParentId();
                Attribute attribute1 = attributeExtMapper.selectByPrimaryKey(parentId.shortValue());
                shopcartVo.setPingZhong(attribute1.getAttrName());


                shopcartVo.setHaveGoods(true);
                BeanUtil.copyProperties(goods, shopcartVo);

                //够取出商品中的 价格 * 数量 算出金额保留两位小数
                BigDecimal shopPrice = goods.getShopPrice();
                BigDecimal money = shopPrice.multiply(new BigDecimal(num));

                shopcartVo.setMoney(money);
                shopcartVo.setShopcartId(shopcart.getId());
            }

            shopcartVoList.add(shopcartVo);
        }



        return shopcartVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateShopcart(Shopcart shopcart) {

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
