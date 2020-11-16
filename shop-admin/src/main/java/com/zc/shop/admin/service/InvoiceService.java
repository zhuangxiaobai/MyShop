package com.zc.shop.admin.service;

import com.zc.shop.mbg.po.Invoice;
import com.zc.shop.mbg.po.Makeout;

import java.util.List;

public interface InvoiceService {
    int createInvoice(Invoice invoice, Integer userId);

    int createMakeOut(Makeout makeout, Integer userId);

    List<Invoice> invoiceList(Integer userId);

    List<Makeout> makeoutList(Integer userId);

    int updateInvoiceById(Invoice invoice);

    int updateMakeoutById(Makeout makeout);
}
