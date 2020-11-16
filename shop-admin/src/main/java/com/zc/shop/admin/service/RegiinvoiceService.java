package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;

import java.util.Map;

public interface RegiinvoiceService {
    int createRegiinvoice(ReginivoiceCreateParam reginivoiceCreateParam);

    int updateStatus(RegiinvoiceStatusParam regiinvoiceStatusParam);

    Map myInRegiinvoice(RegiinvoiceInParam regiinvoiceInParam, Integer userId);

    Map myOutRegiinvoice(RegiinvoiceOutParam regiinvoiceOutParam, Integer userId);

    Map myInRegiinvoiceChaKan(RegiinvoiceInChaKanParam regiinvoiceInChaKanParam, Integer userId);

    Map myOutRegiinvoiceChanKan(RegiinvoiceOutChaKanParam regiinvoiceOutChaKanParam, Integer userId);

    Map regiinvoiceSearch(RegiinvoiceSearchParam regiinvoiceSearchParam);
}
