package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodsCreateParam{


        @ApiModelProperty(value = "品种")
        private String attrCatName;

        @ApiModelProperty(value = "品名")
        private String attrName;

        @ApiModelProperty(value = "规格")
        private String specifications;

        @ApiModelProperty(value = "材质")
        private String material;

        @ApiModelProperty(value = "商标")
        private String trademark;

        @ApiModelProperty(value = "地区,钢厂/产地")
        private String attr1;

        @ApiModelProperty(value = "合作仓储方，关联ware_user表，仓库/港口")
        private Integer wareId;

        @ApiModelProperty(value = "商品重量")
        private BigDecimal goodsWeight;

        @ApiModelProperty(value = "商品数量单位，1为吨，2为千克，3为m3")
        private Short weightUnit;

        @ApiModelProperty(value = "提货地点")
        private String attr2;

        @ApiModelProperty(value = "资源号")
        private String resourceNumber;

        @ApiModelProperty(value = "供应商计划销售数量，可售数量")
        private Short goodsNumber;

        @ApiModelProperty(value = "本网售价")
        private BigDecimal shopPrice;


        @ApiModelProperty(value = "备注")
        private String remark;





}
