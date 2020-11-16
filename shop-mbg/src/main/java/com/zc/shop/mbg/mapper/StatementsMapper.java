package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Statements;

public interface StatementsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Statements record);

    int insertSelective(Statements record);

    Statements selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Statements record);

    int updateByPrimaryKey(Statements record);
}