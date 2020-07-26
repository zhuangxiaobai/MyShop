package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.StoreinfoCreateParam;
import com.zc.shop.admin.mapper.StoreinfoExtMapper;
import com.zc.shop.admin.service.StoreinfoService;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Storeinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StoreinfoServiceImpl implements StoreinfoService {

    @Autowired
    private StoreinfoExtMapper storeinfoExtMapper;


    @Override
    public int create(StoreinfoCreateParam storeinfoCreateParam, Integer userId) {

        Storeinfo storeinfo = new Storeinfo();
        //查是否已经拥有了店铺
        Storeinfo storeinfoExist = storeinfoExtMapper.selectStoreinfoByUserId(userId);
        if(storeinfoExist !=null){
            throw new BusinessException(ResultCode.STOREINFOEXIST);
        }


        BeanUtil.copyProperties(storeinfoCreateParam,storeinfo);
        storeinfo.setCreatedAt(LocalDateTime.now());
        storeinfo.setUserId(userId);
        return storeinfoExtMapper.insertSelective(storeinfo);
    }

    @Override
    public Storeinfo StoreinfoByUserId(Integer userId) {
        return storeinfoExtMapper.selectStoreinfoByUserId(userId);
    }
}
