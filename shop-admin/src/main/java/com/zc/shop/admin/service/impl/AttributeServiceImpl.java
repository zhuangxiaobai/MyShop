package com.zc.shop.admin.service.impl;


import com.zc.shop.admin.mapper.AttributeExtMapper;
import com.zc.shop.admin.service.AttributeService;
import com.zc.shop.admin.vo.AttributeVo;
import com.zc.shop.mbg.po.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeExtMapper attributeExtMapper;


    @Override
    public List<String> allPingZhong() {


        return  attributeExtMapper.selectPingZhongAll();



    }

    @Override
    public List<Attribute> ListByPingZhong(String pingZhong) {


        return  attributeExtMapper.ListByPingZhong(pingZhong);
    }

    @Override
    public List<AttributeVo> AllAttributeVo() {


        return attributeExtMapper.AllAttributeVo();
    }
}
