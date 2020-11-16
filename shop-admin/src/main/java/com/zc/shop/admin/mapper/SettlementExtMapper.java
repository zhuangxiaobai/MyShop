package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.SettlementMapper;
import com.zc.shop.mbg.po.Settlement;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SettlementExtMapper extends SettlementMapper {

    List<Settlement> selectByOrderCode(String orderCode);

    int updateWeightAndNum(@Param("orderId") Long orderId, @Param("shiTiNum") Integer shiTiNum, @Param("shiTiWeight") BigDecimal shiTiWeight, @Param("now") LocalDateTime now);
}