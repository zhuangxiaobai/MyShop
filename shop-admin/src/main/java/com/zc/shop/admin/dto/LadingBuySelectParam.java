package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LadingBuySelectParam {


    @ApiModelProperty(value = "提单编号")
    private String ladingCode;


    @ApiModelProperty(value = "品名")
    private String goodName;

    @ApiModelProperty(value = "规格")
    private String guige;


    @ApiModelProperty(value = "提单状态,不传默认查所有")
    private Integer status;

    @ApiModelProperty(value = "提单时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;

    @ApiModelProperty(value = "提单时间结束")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;

    @ApiModelProperty(value = "仓库/港口名字")
    private String wareName;

    @ApiModelProperty(value = "买家ID，不需要传,后台自动赋值")
    private Integer buyId;

    @ApiModelProperty(value = "公司名，为了去查过户到此公司的提单,不需要传，后台自动赋值")
    private String companyName;



    @ApiModelProperty(value = "起始页")
    private Integer startPage;

    @ApiModelProperty(value = "每页容量")
    private Integer PageSize;






}
