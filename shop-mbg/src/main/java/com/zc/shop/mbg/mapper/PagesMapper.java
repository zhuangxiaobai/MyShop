package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Pages;

public interface PagesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Pages record);

    int insertSelective(Pages record);

    Pages selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Pages record);

    int updateByPrimaryKey(Pages record);
}