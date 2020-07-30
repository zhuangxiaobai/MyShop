package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.LadingBuySelectParam;
import com.zc.shop.admin.dto.LadingCreateParam;
import com.zc.shop.admin.dto.LadingParam;
import com.zc.shop.admin.dto.LadingSellSelectParam;
import com.zc.shop.admin.vo.LadingAllVo;

import java.util.List;
import java.util.Map;

public interface LadingService {
    int create(LadingCreateParam ladingCreateParam, Integer userId);

    int updateLadingStatus(LadingParam ladingParam);

    Map mySellLading(LadingSellSelectParam ladingSelectParam, Integer userId);

    Map myBuyLading(LadingBuySelectParam ladingSelectParam, Integer userId);
}
