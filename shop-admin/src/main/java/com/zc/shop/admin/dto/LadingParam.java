package com.zc.shop.admin.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LadingParam {

    @ApiModelProperty(value = "提单id")
    private Long id;


    @ApiModelProperty(value = "提单状态")
    private Integer status;

    @ApiModelProperty(value = "对应订单id")
    private Integer orderId;





}
