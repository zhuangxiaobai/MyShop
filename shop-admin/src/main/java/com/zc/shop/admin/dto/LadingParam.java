package com.zc.shop.admin.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
   修改提单状态的
 */

@Data
public class LadingParam {

    @ApiModelProperty(value = "提单号")
    private String  ladingCode;


    @ApiModelProperty(value = "提单状态")
    private Integer status;





}
