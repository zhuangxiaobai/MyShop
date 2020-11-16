package com.zc.shop.admin.service;

import com.zc.shop.admin.vo.ShopcartGaiVo;
import com.zc.shop.admin.vo.ShopcartVo;
import com.zc.shop.mbg.po.Shopcart;

import java.util.List;

public interface ShopcartService {
    int addShopcart(Shopcart shopcart);

    List<ShopcartGaiVo> shopcartList(Integer userId);

    int updateShopcart(Shopcart shopcart);

    int deleteShopcart(List<Short> shopcartIds);
}
