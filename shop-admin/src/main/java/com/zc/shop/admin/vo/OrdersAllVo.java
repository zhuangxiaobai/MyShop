package com.zc.shop.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(description="查询订单的时候返回给前端的信息,包含完整信息")
public class OrdersAllVo {

    //订单部分
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "供应商id")
    private Integer orderSupplierId;

    @ApiModelProperty(value = "商品id")
    private Integer orderGoodsId;

    @ApiModelProperty(value = "购买数量")
    private Integer orderNum;

    @ApiModelProperty(value = "重量")
    private BigDecimal orderWeight;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "买家id")
    private Integer orderBuyId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCreatedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderUpdatedAt;

    @ApiModelProperty(value = "余量")
    private BigDecimal orderRemaining;

    @ApiModelProperty(value = "订单留言，备用")
    private String orderRemark;


    //商品部分













}
