package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.InventoryLog;

public interface InventoryLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryLog record);

    int insertSelective(InventoryLog record);

    InventoryLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryLog record);

    int updateByPrimaryKey(InventoryLog record);
}