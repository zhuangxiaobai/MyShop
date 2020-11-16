package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.InvoiceMapper;
import com.zc.shop.mbg.po.Invoice;

import java.util.List;

public interface InvoiceExtMapper extends InvoiceMapper {

    List<Invoice> selectInvoiceByUserId(Integer userId);
}