package com.zc.shop.admin.service;

import com.zc.shop.mbg.po.Attribute;

import java.util.List;

public interface AttributeService {
    List<String> allPingZhong();

    List<Attribute> ListByPingZhong(String pingZhong);
}
