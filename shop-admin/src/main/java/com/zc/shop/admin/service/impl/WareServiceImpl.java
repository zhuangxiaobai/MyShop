package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.WareCreateParam;
import com.zc.shop.admin.dto.WareUpdateParam;
import com.zc.shop.admin.mapper.WareExtMapper;
import com.zc.shop.admin.service.WareService;
import com.zc.shop.mbg.po.Ware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WareServiceImpl implements WareService {

      @Autowired
      private WareExtMapper wareExtMapper;


    @Override
    public List<Ware> allWare() {
        return wareExtMapper.selectAllWare();
    }

    @Override
    public Ware getWareById(Short wareId) {

        return wareExtMapper.selectByPrimaryKey(wareId);
    }

    @Override
    public int createWare(WareCreateParam wareCreateParam) {

        Ware ware = new Ware();
        BeanUtil.copyProperties(wareCreateParam,ware);
        LocalDateTime now = LocalDateTime.now();
        ware.setCreatedAt(now);
        ware.setUpdatedAt(now);



        return wareExtMapper.insertSelective(ware);
    }

    @Override
    public int updateWare(WareUpdateParam wareUpdateParam) {


        Ware ware = new Ware();
        BeanUtil.copyProperties(wareUpdateParam,ware);
        LocalDateTime now = LocalDateTime.now();

        ware.setUpdatedAt(now);


        return wareExtMapper.updateByPrimaryKeyWithBLOBs(ware);
    }
}
