package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Attribute;

public interface AttributeMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Attribute record);

    int insertSelective(Attribute record);

    Attribute selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Attribute record);

    int updateByPrimaryKey(Attribute record);
}