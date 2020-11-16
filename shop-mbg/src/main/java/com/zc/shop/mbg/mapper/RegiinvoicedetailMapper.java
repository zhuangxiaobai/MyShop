package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Regiinvoicedetail;

public interface RegiinvoicedetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Regiinvoicedetail record);

    int insertSelective(Regiinvoicedetail record);

    Regiinvoicedetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Regiinvoicedetail record);

    int updateByPrimaryKey(Regiinvoicedetail record);
}