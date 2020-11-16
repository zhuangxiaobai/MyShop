package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Warehouse;

public interface WarehouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);
}