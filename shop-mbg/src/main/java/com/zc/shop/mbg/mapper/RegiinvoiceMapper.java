package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Regiinvoice;

public interface RegiinvoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Regiinvoice record);

    int insertSelective(Regiinvoice record);

    Regiinvoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Regiinvoice record);

    int updateByPrimaryKey(Regiinvoice record);
}