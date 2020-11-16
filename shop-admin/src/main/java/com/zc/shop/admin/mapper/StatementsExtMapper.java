package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.StatementsMapper;
import com.zc.shop.mbg.po.Statements;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface StatementsExtMapper extends StatementsMapper {
    Statements selectByOrderCode(String orderCode);

    int updateWeightAndNum(@Param("orderCode")String orderCode,@Param("sumNum") int sumNum, @Param("weightSum") BigDecimal weightSum, @Param("priceSum") BigDecimal priceSum);
}
