package com.zc.shop.admin.service;

import com.zc.shop.admin.vo.ShopcartVo;
import com.zc.shop.mbg.po.Shopcart;

import java.util.List;

public interface ShopcartService {
    int addShopcart(Shopcart shopcart);

    List<ShopcartVo> shopcartList(Integer userId);
}
