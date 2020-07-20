package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.po.Distribution;

public interface DistributionExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Distribution record);

    int insertSelective(Distribution record);

    Distribution selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Distribution record);

    int updateByPrimaryKey(Distribution record);
}