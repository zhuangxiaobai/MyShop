package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Factory;

public interface FactoryMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Factory record);

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKeyWithBLOBs(Factory record);

    int updateByPrimaryKey(Factory record);
}