package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.GoodsMapper;
import com.zc.shop.mbg.po.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsExtMapper extends GoodsMapper {

    int updateGoodsNum(@Param("goodId") Integer goodId, @Param("num") Short buyNum);
}