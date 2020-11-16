package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RoleSelectParam {


    @ApiModelProperty(value = "公司id")
    private Integer c_id;

    @ApiModelProperty(value = "角色名字")
    private String name;


    @ApiModelProperty(value = "查询时间开始")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;

    @ApiModelProperty(value = "查询时间结束")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;




    @ApiModelProperty(value = "分页对象")
    private PageParam pageParam;






}
