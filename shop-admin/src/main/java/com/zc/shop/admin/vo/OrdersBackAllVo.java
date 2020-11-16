package com.zc.shop.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zc.shop.mbg.po.Certification;
import com.zc.shop.mbg.po.Margin;
import com.zc.shop.mbg.po.Regiinvoice;
import com.zc.shop.mbg.po.Statements;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersBackAllVo {


     //订单号
     private String orderCode;

     //订单状态
     private Integer status;

     //订单下单时间
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
     private LocalDateTime add_time;

     //公司对象（卖家就卖的显示买家的，买家查买的就显示卖家的）
     private Certification certification;

     private Margin margin;

     private Statements statements;

     private Regiinvoice regiinvoice;

     //总量
     private int numAll;

     //总重
     private BigDecimal weightAll;

     //总价
     private BigDecimal priceAll;


     private List<OrderDetailAllVo> orderDetailAllVoList ;




}
