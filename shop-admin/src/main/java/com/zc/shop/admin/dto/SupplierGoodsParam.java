package com.zc.shop.admin.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(description = "创建订单需要传的对象")
public class SupplierGoodsParam {

      private Integer supplierId;

      private BigDecimal payment;

      private BigDecimal margin;

      private String  remark;


      private List<GoodBuyParam> goodBuyParam;


}
