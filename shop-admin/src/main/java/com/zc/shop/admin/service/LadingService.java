package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;
import com.zc.shop.mbg.po.Lading;

import java.util.List;
import java.util.Map;

public interface LadingService {
    int create(LadingCreateParam ladingCreateParam, Integer userId);

    int updateLadingStatus(LadingParam ladingParam, Integer userId);

    Map mySellLading(LadingSellSelectParam ladingSelectParam, Integer userId);

    Map myBuyLading(LadingBuySelectParam ladingSelectParam, Integer userId);

    int createLadings(List<Lading> ladingList, Integer userId);

    int shiTiParam(List<ShiTiParam> shiTiParam);

   Map selectShitichuku(ShiTiChuKuParam shiTiChuKuParam);
}
