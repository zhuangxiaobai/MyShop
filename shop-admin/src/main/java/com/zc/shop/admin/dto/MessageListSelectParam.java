package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageListSelectParam {

    @ApiModelProperty("查询通知的用户id,必要")
    private Integer id;

    @ApiModelProperty("查询状态，不传为全部,0是代办,1是私信,2是系统")
    private Integer type;


    @ApiModelProperty("查询状态，0未读，1已读，不传为全部")
    private Integer status;



    @ApiModelProperty("分页对象")
    private PageParam pageParam;



}
