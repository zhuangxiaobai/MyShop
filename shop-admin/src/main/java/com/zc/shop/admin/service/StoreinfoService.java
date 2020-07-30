package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.StoreinfoCreateParam;
import com.zc.shop.admin.dto.StoreinfoDetailParam;
import com.zc.shop.admin.dto.StoreinfoListParam;
import com.zc.shop.admin.dto.StoreinfoUpdateParam;
import com.zc.shop.admin.vo.StoreinfoBuyDetailsVo;
import com.zc.shop.admin.vo.StoreinfoListVo;
import com.zc.shop.mbg.po.Storeinfo;

import java.util.Map;

public interface StoreinfoService {
    int create(StoreinfoCreateParam storeinfoCreateParam, Integer userId);

    Storeinfo StoreinfoByUserId(Integer userId);

    StoreinfoListVo StoreinfoList(StoreinfoListParam storeinfoListParam);

    StoreinfoBuyDetailsVo StoreinfoDetails(StoreinfoDetailParam storeinfoDetailParam);

    Map storeinfoDetailsSelectParam(Short storeinfoId);

    int updateStoreinfo(StoreinfoUpdateParam storeinfoUpdateParam);
}
