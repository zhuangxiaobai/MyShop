package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.po.Ware;

public interface WareExtMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Ware record);

    int insertSelective(Ware record);

    Ware selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Ware record);

    int updateByPrimaryKeyWithBLOBs(Ware record);

    int updateByPrimaryKey(Ware record);
}