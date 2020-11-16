package com.zc.shop.admin.dto;

import lombok.Data;

//卖家的进项发票查看的参数
@Data
public class RegiinvoiceInChaKanParam {


    private String orderCode;

    //卖家公司名
    private String kaidanwei;

    //买家公司名
    private String danwei;

}
