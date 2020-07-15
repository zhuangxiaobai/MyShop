package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Ware;

public interface WareMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Ware record);

    int insertSelective(Ware record);

    Ware selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Ware record);

    int updateByPrimaryKeyWithBLOBs(Ware record);

    int updateByPrimaryKey(Ware record);
}