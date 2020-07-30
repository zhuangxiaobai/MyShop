package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreinfoUpdateParam {


    @ApiModelProperty(value = "店铺id")
    private Short id;

    @ApiModelProperty(value = "店铺名称")
    private String title;

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private Integer phone;

    @ApiModelProperty(value = "经营范围，主营")
    private String business;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地址，不用")
    private String address;

    @ApiModelProperty(value = "logo")
    private String logo;


    @ApiModelProperty(value = "不传")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "省份")
    private String provinces;

    @ApiModelProperty(value = "店铺介绍")
    private String content;



}
