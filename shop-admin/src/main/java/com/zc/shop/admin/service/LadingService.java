package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.LadingBuySelectParam;
import com.zc.shop.admin.dto.LadingCreateParam;
import com.zc.shop.admin.dto.LadingParam;
import com.zc.shop.admin.dto.LadingSellSelectParam;
import com.zc.shop.admin.vo.LadingAllVo;

import java.util.List;

public interface LadingService {
    int create(LadingCreateParam ladingCreateParam, Integer userId);

    int updateLadingStatus(LadingParam ladingParam);

    List<LadingAllVo> mySellLading(LadingSellSelectParam ladingSelectParam, Integer userId);

    List<LadingAllVo> myBuyLading(LadingBuySelectParam ladingSelectParam, Integer userId);
}
