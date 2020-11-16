package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.*;
import lombok.Data;

//包含订单表，商品表，认证表，结算详情表，开票详情表，
@Data
public class RegiinvoiceDetailChaKanVo {


    private Order order;

    private Goods goods;

    private Certification certificationSell;

    private Certification certificationBuy;

    private Regiinvoicedetail regiinvoicedetail;

    private Settlement settlement;




}
