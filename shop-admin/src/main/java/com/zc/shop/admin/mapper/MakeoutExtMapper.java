package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.MakeoutMapper;
import com.zc.shop.mbg.po.Makeout;

import java.util.List;

public interface MakeoutExtMapper extends MakeoutMapper {

    List<Makeout> selectMakeoutByUserId(Integer userId);
}
