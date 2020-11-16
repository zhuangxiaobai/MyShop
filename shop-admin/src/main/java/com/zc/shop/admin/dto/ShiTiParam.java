package com.zc.shop.admin.dto;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Data
public class ShiTiParam {


    private Long ladingId;


    private Integer shiTiNum;


    private BigDecimal shiTiWeight;




}
