package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Invoice;

public interface InvoiceMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    Invoice selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKeyWithBLOBs(Invoice record);

    int updateByPrimaryKey(Invoice record);
}