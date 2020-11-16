package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.RegiinvoiceInChaKanParam;
import com.zc.shop.admin.dto.RegiinvoiceOutChaKanParam;
import com.zc.shop.admin.vo.RegiinvoiceDetailChaKanVo;
import com.zc.shop.mbg.mapper.RegiinvoicedetailMapper;

import java.util.List;

public interface RegiinvoicedetailExtMapper extends RegiinvoicedetailMapper {
    List<RegiinvoiceDetailChaKanVo> selectRegiinvoiceInDetailChaKan(RegiinvoiceInChaKanParam regiinvoiceInChaKanParam);

    List<RegiinvoiceDetailChaKanVo> selectRegiinvoiceOutDetailChaKan(RegiinvoiceOutChaKanParam regiinvoiceOutChaKanParam);
}
