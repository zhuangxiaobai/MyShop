package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.StoreinfoCreateParam;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Storeinfo;

public interface StoreinfoService {
    int create(StoreinfoCreateParam storeinfoCreateParam, Integer userId);

    Storeinfo StoreinfoByUserId(Integer userId);
}
