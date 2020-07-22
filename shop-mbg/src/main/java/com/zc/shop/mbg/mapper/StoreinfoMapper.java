package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Storeinfo;

public interface StoreinfoMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Storeinfo record);

    int insertSelective(Storeinfo record);

    Storeinfo selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Storeinfo record);

    int updateByPrimaryKeyWithBLOBs(Storeinfo record);

    int updateByPrimaryKey(Storeinfo record);
}