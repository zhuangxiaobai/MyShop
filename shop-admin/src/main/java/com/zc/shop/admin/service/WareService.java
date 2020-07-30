package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.WareCreateParam;
import com.zc.shop.admin.dto.WareUpdateParam;
import com.zc.shop.mbg.po.Ware;

import java.util.List;

public interface WareService {
    List<Ware> allWare();

    Ware getWareById(Short wareId);

    int createWare(WareCreateParam wareCreateParam);

    int updateWare(WareUpdateParam wareUpdateParam);
}
