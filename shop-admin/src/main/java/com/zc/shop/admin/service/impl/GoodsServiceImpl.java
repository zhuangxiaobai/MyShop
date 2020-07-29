package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.GoodsCreateParam;
import com.zc.shop.admin.dto.GoodsSelectParam;
import com.zc.shop.admin.dto.StoreinfoListParam;
import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.admin.vo.GoodsAllInfoVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Attribute;
import com.zc.shop.mbg.po.Goods;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private AttributeExtMapper attributeExtMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(GoodsCreateParam goodsCreateParam, Integer userId) {

       //获取品种,品名,规格,材质 1.已经在attribute表中存在不管 2.不存在，添加
        Attribute  attribute = new Attribute();
        //获取品种，品种是写死的
         String pingZhong = goodsCreateParam.getAttrCatName();

         //获取品名，规格，材质
        attribute.setAttrName(goodsCreateParam.getAttrName());
        attribute.setSpecifications(goodsCreateParam.getSpecifications());
        attribute.setMaterial(goodsCreateParam.getMaterial());


        //去查询Attribute表看看存不存在品名，规格，材质相同的信息，不存在就添加然后赋值catId，存在就直接是catId
        Short  catId=  attributeExtMapper.selectAttributeCountExistByCreate(pingZhong,attribute);
         //不存在，添加
        if(catId == null || !(catId > 0)){
            Short parentId  = attributeExtMapper.selectIdByAttrName(pingZhong);
            //品种不存在返回
            if(parentId == null){
                throw new BusinessException(ResultCode.PINGZHONGNOTEXIST);
            }
            attribute.setParentId(parentId.intValue());
            Integer createSuccess =  attributeExtMapper.insertSelective(attribute);
            catId = createSuccess.shortValue();
        }

        Goods goods = new Goods();

        //存在，不管，继续添加goods表

        //1.资源号
        String resourceNumber = goodsCreateParam.getResourceNumber();
        String trademark = goodsCreateParam.getTrademark();
        String attr1 = goodsCreateParam.getAttr1();
        Integer wareId = goodsCreateParam.getWareId();
        BigDecimal goodsWeight = goodsCreateParam.getGoodsWeight();
        Short weightUnit = goodsCreateParam.getWeightUnit();
        String attr2 = goodsCreateParam.getAttr2();
        Short goodsNumber = goodsCreateParam.getGoodsNumber();
        BigDecimal shopPrice = goodsCreateParam.getShopPrice();
        String remark =  goodsCreateParam.getRemark();

      //------------------------------------------------------
        goods.setCatId(catId);
       //商品货号
        goods.setGoodsSn(resourceNumber);
        //商品品名
        goods.setGoodsName(goodsCreateParam.getAttrName());
        //商标
        goods.setTrademark(trademark);
        goods.setWareId(wareId);
        goods.setGoodsWeight(goodsWeight);
        goods.setWeightUnit(weightUnit);
        goods.setAttr1(attr1);
        goods.setAttr2(attr2);
        goods.setGoodsNumber(goodsNumber);
        goods.setShopPrice(shopPrice);
        goods.setRemark(remark);

        goods.setAddTime(LocalDateTime.now());
        goods.setSuppliersId(userId.shortValue());

  //----添加goods表数据--------------------------------------------------
        int i = goodsExtMapper.insertSelective(goods);



        return i ;
    }

    @Override
    public Map goodslistIndex(GoodsSelectParam goodsSelectParam) {


        //分页查询处理
        Integer startPage = goodsSelectParam.getPageParam().getStartPage();
        Integer pageSize = goodsSelectParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        goodsSelectParam.getPageParam().setStartPage(start);


        List<GoodsAllInfoVo> goodsAllInfoVoList =   goodsExtMapper.selectGoodsAllIndex(goodsSelectParam);
        int goodsAllInfoVoListNum =   goodsExtMapper.selectGoodsAllIndexNum(goodsSelectParam);

        Map map = new HashMap();
        map.put("goodsAllInfoVoList",goodsAllInfoVoList);
        map.put("total",goodsAllInfoVoListNum);
        return map;
    }
}
