package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Shopcart;

public interface ShopcartMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    Shopcart selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Shopcart record);

    int updateByPrimaryKeyWithBLOBs(Shopcart record);

    int updateByPrimaryKey(Shopcart record);
}