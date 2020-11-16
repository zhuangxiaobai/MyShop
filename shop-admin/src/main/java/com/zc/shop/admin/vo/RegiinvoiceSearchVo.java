package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Statements;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value="发票查询返回对象")
@Data
public class RegiinvoiceSearchVo {

    @ApiModelProperty(value = "订货单位")
    private String companyName;

    @ApiModelProperty(value = "结算总表")
    private Statements statements;

    @ApiModelProperty(value = "结算总表")
    private List<RegiinvoiceSimpleVo> regiinvoiceSimpleVoList;


}
