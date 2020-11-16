package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.MarginMapper;
import com.zc.shop.mbg.po.Margin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarginExtMapper extends MarginMapper {

    Margin selectMarginByOrderCode(@Param("orderCode") String orderCode);
}
