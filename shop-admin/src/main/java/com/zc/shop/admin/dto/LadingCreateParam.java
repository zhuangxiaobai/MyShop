package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LadingCreateParam implements Serializable {


    private Long id;

    @ApiModelProperty(value = "提单编号")
    private String code;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "供应商id")
    private Integer supplierId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "买家申请提货重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "提单状态")
    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ladingedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "对应订单id")
    private Integer orderId;

    @ApiModelProperty(value = "0为整单1为分批2为过户")
    private Integer type;

    @ApiModelProperty(value = "过户单位")
    private String guohu;

    @ApiModelProperty(value = "实提件数")
    private Integer realNum;

    @ApiModelProperty(value = "实提重量")
    private BigDecimal realWeight;

    @ApiModelProperty(value = "买家申请提单数量")
    private Integer num;

    @ApiModelProperty(value = "提单留言")
    private String remark;

    @ApiModelProperty(value = "提单图片")
    private String pic;

    @ApiModelProperty(value = "司机信息等json格式")
    private String information;

    @ApiModelProperty(value = "授权书图片地址")
    private String shouquanshu;

}
