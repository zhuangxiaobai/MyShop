package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Margin;

public interface MarginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Margin record);

    int insertSelective(Margin record);

    Margin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Margin record);

    int updateByPrimaryKeyWithBLOBs(Margin record);

    int updateByPrimaryKey(Margin record);
}