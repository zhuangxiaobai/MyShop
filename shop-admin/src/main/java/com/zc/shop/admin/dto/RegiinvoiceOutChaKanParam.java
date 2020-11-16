package com.zc.shop.admin.dto;

import lombok.Data;

@Data
public class RegiinvoiceOutChaKanParam {

    private String orderCode;

    //卖家公司名
    private String kaidanwei;

    //买家公司名
    private String danwei;

}
