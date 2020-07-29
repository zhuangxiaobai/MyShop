package com.zc.shop.admin.service.impl;


import com.zc.shop.admin.dto.GoodsSelectAttrChangeParam;
import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.service.AttributeService;
import com.zc.shop.admin.vo.AttributeVo;
import com.zc.shop.mbg.po.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeExtMapper attributeExtMapper;

   @Autowired
   private GoodsExtMapper goodsExtMapper;


    @Override
    public List<String> allPingZhong() {


        return  attributeExtMapper.selectPingZhongAll();



    }

    @Override
    public List<Attribute> ListByPingZhong(String pingZhong) {


        return  attributeExtMapper.ListByPingZhong(pingZhong);
    }

    @Override
    public  List<Map> AllAttributeVo() {
        List<AttributeVo> attribureVoList = attributeExtMapper.AllAttributeVo();

        List<String> pingZhongLists = attributeExtMapper.selectPingZhongAll();

        List<Map>  maps = new ArrayList<>();

        for(String pingZhong :pingZhongLists){
            Map map = new HashMap();

            Set attrNameSet = new TreeSet();
            Set specificationsSet = new TreeSet();
            Set materialSet = new TreeSet();
        for(AttributeVo attribute:attribureVoList) {

            if (pingZhong.equals(attribute.getAttrPingZhong())) {
               if(attribute.getAttrAttrName()!=null) {
                   attrNameSet.add(attribute.getAttrAttrName());
               }
               if(attribute.getAttrSpecifications() !=null) {
                   specificationsSet.add(attribute.getAttrSpecifications());
               }
               if(attribute.getAttrMaterial() !=null) {
                   materialSet.add(attribute.getAttrMaterial());
               }
            }


        }
            map.put("pingZhong",pingZhong);
            map.put("attrNameSet",attrNameSet);
            map.put("specificationsSet",specificationsSet);
            map.put("materialSet",materialSet);
           maps.add(map);
       }


        return maps;
    }

    @Override
    public Map GetGoodsSelect() {
        Map map = new HashMap();

        //获取交货地集合
        List<String> attr2All = goodsExtMapper.selectAttr2All();

        //获取厂家集合
        List<String> attr1All = goodsExtMapper.selectAttr1All();

        //获取品种集合
        List<String> pingZhongList = attributeExtMapper.selectPingZhongAll();

       //获取品名集合
        List<String> attrNameList = attributeExtMapper.selectAttrNameAll();

        //获取材质集合
        List<String> materialList = attributeExtMapper.selectmaterialAll();

        //获取规格集合
        List<String> specificationsList = attributeExtMapper.selectspecificationsAll();




        map.put("pingZhongList",pingZhongList);
        map.put("attrNameList",attrNameList);
        map.put("materialList",materialList);
        map.put("specificationsList",specificationsList);
        map.put("attr1All",attr1All);
        map.put("attr2All",attr2All);

        return map;
    }

    @Override
    public Map getGoodsSelectAttributeChange(GoodsSelectAttrChangeParam goodsSelectAttrChangeParam) {

        Map map = new HashMap();

        //获取材质集合
        List<String> materialList = attributeExtMapper.selectmaterialAllByPingZhongAndAttrNameList(goodsSelectAttrChangeParam);

        //获取规格集合
        List<String> specificationsList = attributeExtMapper.selectspecificationsAllByPingZhongAndAttrNameList(goodsSelectAttrChangeParam);


        map.put("materialList",materialList);
        map.put("specificationsList",specificationsList);

        return map;
    }
}
