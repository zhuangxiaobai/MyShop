package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Makeout;

public interface MakeoutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Makeout record);

    int insertSelective(Makeout record);

    Makeout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Makeout record);

    int updateByPrimaryKeyWithBLOBs(Makeout record);

    int updateByPrimaryKey(Makeout record);
}