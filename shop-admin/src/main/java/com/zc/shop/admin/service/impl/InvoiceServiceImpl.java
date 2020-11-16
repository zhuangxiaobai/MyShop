package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.mapper.InvoiceExtMapper;
import com.zc.shop.admin.mapper.MakeoutExtMapper;
import com.zc.shop.admin.service.InvoiceService;
import com.zc.shop.mbg.po.Invoice;
import com.zc.shop.mbg.po.Makeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {


    @Autowired
    private InvoiceExtMapper invoiceExtMapper;

    @Autowired
    private MakeoutExtMapper makeoutExtMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createInvoice(Invoice invoice, Integer userId) {


        LocalDateTime now = LocalDateTime.now();
        invoice.setCreatedAt(now);
        invoice.setUpdatedAt(now);
        invoice.setUserId(userId);


       int i = invoiceExtMapper.insertSelective(invoice);

        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createMakeOut(Makeout makeout, Integer userId) {

        LocalDateTime now = LocalDateTime.now();
        makeout.setCreatedAt(now);
        makeout.setUpdatedAt(now);
        makeout.setUserId(userId);


        int i = makeoutExtMapper.insertSelective(makeout);

        return i;
    }

    @Override
    public List<Invoice> invoiceList(Integer userId) {

        return invoiceExtMapper.selectInvoiceByUserId(userId);
    }

    @Override
    public List<Makeout> makeoutList(Integer userId) {




        return makeoutExtMapper.selectMakeoutByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateInvoiceById(Invoice invoice) {

        LocalDateTime now = LocalDateTime.now();
        invoice.setUpdatedAt(now);
        return invoiceExtMapper.updateByPrimaryKeyWithBLOBs(invoice);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMakeoutById(Makeout makeout) {
        LocalDateTime now = LocalDateTime.now();
        makeout.setUpdatedAt(now);
        return makeoutExtMapper.updateByPrimaryKeyWithBLOBs(makeout);
    }
}
