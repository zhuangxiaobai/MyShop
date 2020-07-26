package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.mapper.WareExtMapper;
import com.zc.shop.admin.service.WareService;
import com.zc.shop.mbg.po.Ware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareServiceImpl implements WareService {

      @Autowired
      private WareExtMapper wareExtMapper;


    @Override
    public List<Ware> allWare() {
        return wareExtMapper.selectAllWare();
    }
}
