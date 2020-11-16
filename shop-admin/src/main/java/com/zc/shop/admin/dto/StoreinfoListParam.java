package com.zc.shop.admin.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreinfoListParam {

    @ApiModelProperty(value = "店铺名字")
     private String storeinfoName;

    @ApiModelProperty(value = "分页对象")
     private PageParam pageParam;
}
