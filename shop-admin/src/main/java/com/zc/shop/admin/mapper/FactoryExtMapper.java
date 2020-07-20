package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.po.Factory;

public interface FactoryExtMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Factory record);

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKeyWithBLOBs(Factory record);

    int updateByPrimaryKey(Factory record);
}