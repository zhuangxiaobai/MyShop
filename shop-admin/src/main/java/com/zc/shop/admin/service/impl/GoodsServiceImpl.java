package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.admin.vo.GoodsAllInfoVo;
import com.zc.shop.mbg.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private AttributeExtMapper attributeExtMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;


    @Autowired
    private OrderExtMapper orderExtMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<GoodsCreateParam> goodsCreateParamList, Integer userId) {

        LocalDateTime now = LocalDateTime.now();

        for(GoodsCreateParam goodsCreateParam:goodsCreateParamList) {

            Goods goods = new Goods();
            //存在，不管，继续添加goods表

            BeanUtil.copyProperties(goodsCreateParam,goods);
            //设置余量为计划销售数量/重量
            goods.setRemainNumber(goodsCreateParam.getGoodsNumber());
            goods.setRemainWeight(goodsCreateParam.getGoodsWeight());
            goods.setAddTime(now);
            goods.setSuppliersId(userId.shortValue());

            //----添加goods表数据--------------------------------------------------
            goodsExtMapper.insertSelective(goods);
        }



        return goodsCreateParamList.size() ;
    }

    @Override
    public Map goodslistIndex(GoodsSelectGaiParam goodsSelectGaiParam) {


        //分页查询处理
        Integer startPage = goodsSelectGaiParam.getPageParam().getStartPage();
        Integer pageSize = goodsSelectGaiParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        goodsSelectGaiParam.getPageParam().setStartPage(start);


        List<Goods> goodsInfoList =   goodsExtMapper.selectGoodsAllIndex(goodsSelectGaiParam);
        int total =   goodsExtMapper.selectGoodsAllIndexNum(goodsSelectGaiParam);
      

        Map map = new HashMap();
        map.put("goodsAllInfoList",goodsInfoList);
        map.put("total",total);
        return map;
    }

    @Override
    public Map goodsSelllist(GoodsSellListParam goodsSellListParam) {


        //分页查询处理
        Integer startPage = goodsSellListParam.getPageParam().getStartPage();
        Integer pageSize = goodsSellListParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        goodsSellListParam.getPageParam().setStartPage(start);


        List<Goods> goodsAllInfoVoList =   goodsExtMapper.selectGoodsSelllist(goodsSellListParam);
        int total  = goodsExtMapper.selectGoodsSelllistNum(goodsSellListParam);

    /*    List<Map> goodsAllInfoVoMap = new ArrayList<>();
        for(Goods goods:goodsAllInfoVoList){
          map.put("goodsAllInfoVo",goodsAllInfoVo);
          map.put("yishoushuliang",yishou);
          goodsAllInfoVoMap.add(map);
        }*/

        Map mapReturn = new HashMap();
        mapReturn.put("goodsInfoVoListMap",goodsAllInfoVoList);
        mapReturn.put("total",total);
        return mapReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateIsOnSale(GoodsUpdateOnSaleParam goodsUpdateOnSaleParam) {

        Goods goods = new Goods();
        goods.setId(goodsUpdateOnSaleParam.getGoodsId());
        goods.setIsOnSale(goodsUpdateOnSaleParam.getIsOnsale());

        return    goodsExtMapper.updateByPrimaryKeySelective(goods);



    }
//
//    @Override
//    public Map selectGoodsById(Integer goodsId) {
//
//
//        Goods goods = goodsExtMapper.selectByPrimaryKey(goodsId);
//
//        Attribute attribute = attributeExtMapper.selectByPrimaryKey(goods.getCatId());
//
//
//        Attribute attribute1 = attributeExtMapper.selectByPrimaryKey(attribute.getParentId().shortValue());
//
//
//
//        Map map  = new HashMap();
//
//
//        map.put("goods",goods);
//        map.put("pingZhong",attribute1.getAttrName());
//        map.put("pingZhong",attribute.getAttrName());
//        map.put("specifications",attribute.getSpecifications());
//        map.put("material",attribute.getMaterial());
//
//
//        return map;
//    }
//
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGoodsInfo(GoodsUpdateParam goodsUpdateParam) {


        Goods goods = new Goods();

        BeanUtil.copyProperties(goodsUpdateParam,goods);



        return goodsExtMapper.updateByPrimaryKeySelective(goods);
    }
}
