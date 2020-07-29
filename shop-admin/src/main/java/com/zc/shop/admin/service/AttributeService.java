package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.GoodsSelectAttrChangeParam;
import com.zc.shop.admin.vo.AttributeVo;
import com.zc.shop.mbg.po.Attribute;

import java.util.List;
import java.util.Map;

public interface AttributeService {
    List<String> allPingZhong();

    List<Attribute> ListByPingZhong(String pingZhong);

    List<Map> AllAttributeVo();

    Map GetGoodsSelect();

    Map getGoodsSelectAttributeChange(GoodsSelectAttrChangeParam goodsSelectAttrChangeParam);
}
