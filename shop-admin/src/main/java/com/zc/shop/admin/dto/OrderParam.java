package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderParam {

    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "不用传此字段,后台用")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;


}
