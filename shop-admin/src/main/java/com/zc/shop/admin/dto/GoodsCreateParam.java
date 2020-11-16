package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodsCreateParam{

        @ApiModelProperty(value = "资源号")
        private String goodsSn;

        @ApiModelProperty(value = "商品品名")
        private String goodsName;

        @ApiModelProperty(value = "供应商计划销售数量")
        private Short goodsNumber;

        @ApiModelProperty(value = "商品重量")
        private BigDecimal goodsWeight;

//        @ApiModelProperty(value = "商品数量单位，1为吨，2为千克，3为m3")
//        private Short weightUnit;

        @ApiModelProperty(value = "本网售价")
        private BigDecimal shopPrice;

        @ApiModelProperty(value = "1为预售2为期货3为定向")
        private Integer isOnSale;

        @ApiModelProperty(value = "前端不传此字段")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime addTime;

        @ApiModelProperty(value = "交货")
        private String address;

        @ApiModelProperty(value = "商品供应商ID，关联suppliers表")
        private Short suppliersId;

        @ApiModelProperty(value = "仓库")
        private String warehouse;

        @ApiModelProperty(value = "备注")
        private String remark;

        @ApiModelProperty(value = "0过磅1理计2抄码")
        private Integer weitype;

        @ApiModelProperty(value = "商品类型")
        private String type;

        @ApiModelProperty(value = "规格")
        private String guige;

        @ApiModelProperty(value = "材质")
        private String caizhi;

        @ApiModelProperty(value = "钢厂")
        private String factory;

        @ApiModelProperty(value = "单位")
        private String danwei;


//
//        @ApiModelProperty(value = "商品数量单位，1为吨，2为千克，3为m3")
//        private Short weightUnit;





}
