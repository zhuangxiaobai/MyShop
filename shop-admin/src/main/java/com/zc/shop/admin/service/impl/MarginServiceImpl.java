package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.mapper.MarginExtMapper;
import com.zc.shop.admin.service.MarginService;
import com.zc.shop.mbg.po.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MarginServiceImpl implements MarginService {


    @Autowired
    private MarginExtMapper marginExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(Margin margin) {

        LocalDateTime now = LocalDateTime.now();
        margin.setCreatedAt(now);
        margin.setUpdatedAt(now);




        return marginExtMapper.insertSelective(margin);
    }

    @Override
    public Margin selectMarginByOrderCode(String orderCode) {



        return marginExtMapper.selectMarginByOrderCode(orderCode);
    }
}
