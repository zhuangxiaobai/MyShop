package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.admin.vo.GoodsAllInfoVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Goods updateGoodNum(int goodId,int jiaorjian, int num) {



        //去查询现有的商品,查到现在商品的余量(数量和重量)
        Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

        //看商品是否下架
        if( goods.getIsDelete() == 1){
            //下架了
            throw new BusinessException(ResultCode.GOODSISNOTONSALE);

        }

        //获取现有的商品的数量和重量
        Short remainNumber = goods.getRemainNumber();
        BigDecimal remainWeight = goods.getRemainWeight();

        //计算单个商品重量
        Short goodsNumber = goods.getGoodsNumber();
        BigDecimal goodsWeight = goods.getGoodsWeight();
        BigDecimal oneWeight = goodsWeight.divide(new BigDecimal(goodsNumber), 4, RoundingMode.HALF_UP);

        //如果传入的是0加，直接加上数量和重量
        if(jiaorjian == 0){
            //设置加后的数量
            goods.setRemainNumber((short) (remainNumber.intValue() + num));

            //设置加后的重量
            remainWeight = remainWeight.add(oneWeight.multiply(new BigDecimal(num)));
            goods.setRemainWeight(remainWeight);
        } //如果传入的是1减,直接减去数量和重量，(如果数量低于0的,操作失败)
        else if(jiaorjian == 1){
            //确认数量是否小于0
            Short numAfter = Integer.valueOf(remainNumber - num).shortValue();
            if(numAfter < 0){
                throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
            }

            //设置减后的数量
            goods.setRemainNumber(numAfter);

            //设置减后的重量
            remainWeight = remainWeight.subtract(oneWeight.multiply(new BigDecimal(num)));
            goods.setRemainWeight(remainWeight);

        }else{
            throw new BusinessException("传入的参数不符合规则,0或1");
        }


        int goodsSuccess = goodsExtMapper.updateByPrimaryKeySelective(goods);
        if(goodsSuccess != 1){
            throw new BusinessException("修改商品数量时失败："+"商品id为"+goods.getId());

        }


        return goods;

    }


}
