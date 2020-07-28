package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LadingCreateParam {



    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "供应商id")
    private Integer supplierId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "提货重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "提单状态")
    private Integer status;

    @ApiModelProperty(value = "对应订单id")
    private Integer orderId;

    @ApiModelProperty(value = "提单留言")
    private String remark;

    @ApiModelProperty(value = "提单图片")
    private String pic;

    @ApiModelProperty(value = "提货时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ladingedAt;

    @ApiModelProperty(value = "不要传")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "不要传")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;




}
