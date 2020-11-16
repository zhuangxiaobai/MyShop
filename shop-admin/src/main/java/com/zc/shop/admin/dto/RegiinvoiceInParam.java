package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.time.LocalDate;

@Data
public class RegiinvoiceInParam {

    //private Integer supplierId;

    private String invoiceNumber;

    @ApiModelProperty(value = "提单时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;

    @ApiModelProperty(value = "提单时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;

    private Integer status;

    //收票单位
    private String companyName;

    //开票单位
    private String kaiCompanyName;


    @ApiModelProperty(value = "起始页")
    private Integer startPage;

    @ApiModelProperty(value = "每页容量")
    private Integer PageSize;



}
