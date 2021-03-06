package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShiTiChuKuParam {

    @ApiModelProperty(value = "提单编号")
    private String ladingCode;


    @ApiModelProperty(value = "品名")
    private String goodName;

    @ApiModelProperty(value = "规格")
    private String guige;


    @ApiModelProperty(value = "提单状态")
    private Integer status;

    @ApiModelProperty(value = "提单时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;

    @ApiModelProperty(value = "提单时间结束")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;

    @ApiModelProperty(value = "仓库/港口名字")
    private String wareName;


    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "供应商id，需要传")
    private Integer supplierId;


    @ApiModelProperty(value = "起始页")
    private Integer startPage;

    @ApiModelProperty(value = "每页容量")
    private Integer PageSize;
}
