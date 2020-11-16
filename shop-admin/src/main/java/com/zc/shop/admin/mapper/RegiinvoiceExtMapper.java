package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.RegiinvoiceInParam;
import com.zc.shop.admin.dto.RegiinvoiceOutParam;
import com.zc.shop.admin.dto.RegiinvoiceSearchParam;
import com.zc.shop.mbg.mapper.RegiinvoiceMapper;
import com.zc.shop.mbg.po.Regiinvoice;

import java.util.List;

public interface RegiinvoiceExtMapper extends RegiinvoiceMapper {
    List<Regiinvoice> selectRegiinvoiceIn(RegiinvoiceInParam regiinvoiceInParam);

    Integer selectRegiinvoiceInTotal(RegiinvoiceInParam regiinvoiceInParam);

    List<Regiinvoice> selectRegiinvoiceOut(RegiinvoiceOutParam regiinvoiceOutParam);

    Integer selectRegiinvoiceOutTotal(RegiinvoiceOutParam regiinvoiceInParam);

    List<String> selectSearchOrderCode(RegiinvoiceSearchParam regiinvoiceSearchParam);

    Integer selectSearchOrderCodeNum(RegiinvoiceSearchParam regiinvoiceSearchParam);
}
