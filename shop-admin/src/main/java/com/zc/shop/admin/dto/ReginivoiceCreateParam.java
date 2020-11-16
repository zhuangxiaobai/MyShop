package com.zc.shop.admin.dto;

import com.zc.shop.mbg.po.Regiinvoice;
import com.zc.shop.mbg.po.Regiinvoicedetail;
import lombok.Data;

import java.util.List;

@Data
public class ReginivoiceCreateParam {


    private Regiinvoice regiinvoice;


    private List<Regiinvoicedetail> regiinvoicedetailList;


}
