package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.*;
import lombok.Data;

import java.util.List;

@Data
public class OrdersGaiVo {


    private Order order;    //订单表信息

    private Goods goods;   //商品表信息

    private Certification certification; //认证表信息


    private List<Lading> ladingList; //多个提单表信息


    //保证金表
    private Margin margin;

    //结算表
    private Statements statements;


   //结算详情表
    private Settlement settlement;


    //发票表
    private Regiinvoice regiinvoice;


    //发票详情表
    private Regiinvoicedetail regiinvoicedetail;



}
