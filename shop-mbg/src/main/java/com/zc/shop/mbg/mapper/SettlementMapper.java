package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Settlement;

public interface SettlementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Settlement record);

    int insertSelective(Settlement record);

    Settlement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Settlement record);

    int updateByPrimaryKey(Settlement record);
}