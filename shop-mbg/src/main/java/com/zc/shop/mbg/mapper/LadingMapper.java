package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Lading;

public interface LadingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Lading record);

    int insertSelective(Lading record);

    Lading selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Lading record);

    int updateByPrimaryKeyWithBLOBs(Lading record);

    int updateByPrimaryKey(Lading record);
}