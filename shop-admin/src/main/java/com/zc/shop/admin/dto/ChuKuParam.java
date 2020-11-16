package com.zc.shop.admin.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChuKuParam {

    //当前出库人id
    private Integer userId;


    private String name;

    private String guige;

    private String caizhi;


    private Integer num;

    private BigDecimal weight;

}
