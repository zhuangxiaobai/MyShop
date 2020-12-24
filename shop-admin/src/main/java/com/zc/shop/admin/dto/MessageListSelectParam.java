package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageListSelectParam {

    @ApiModelProperty("查询通知的用户id,必要")
    private Integer id;

    @ApiModelProperty("查询状态，0为全部,1为系统,2为私信,3为代办,必要")
    private Integer status;


    @ApiModelProperty("分页对象")
    private PageParam pageParam;



}
