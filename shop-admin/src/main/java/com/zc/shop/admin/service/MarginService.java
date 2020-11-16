package com.zc.shop.admin.service;

import com.zc.shop.mbg.po.Margin;

public interface MarginService {
    int create(Margin margin);

    Margin selectMarginByOrderCode(String orderCode);
}
