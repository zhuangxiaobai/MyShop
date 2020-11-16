package com.zc.shop.admin.mapper;

import com.zc.shop.admin.vo.PagesVo;
import com.zc.shop.mbg.mapper.PagesMapper;

import java.util.List;

public interface PagesExtMapper extends PagesMapper {
    List<PagesVo> selectMenuTree();
}
