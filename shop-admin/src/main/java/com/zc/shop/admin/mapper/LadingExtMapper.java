package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.LadingBuySelectParam;
import com.zc.shop.admin.dto.LadingSellSelectParam;
import com.zc.shop.admin.vo.LadingAllVo;
import com.zc.shop.mbg.mapper.LadingMapper;
import com.zc.shop.mbg.po.Lading;

import java.util.List;

public interface LadingExtMapper extends LadingMapper {

    List<LadingAllVo> selectMyBuyLading(LadingBuySelectParam ladingSelectParam);

    List<LadingAllVo> selectMySellLading(LadingSellSelectParam ladingSelectParam);
}