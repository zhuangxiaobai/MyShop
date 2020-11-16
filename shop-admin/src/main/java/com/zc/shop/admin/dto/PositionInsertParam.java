package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PositionInsertParam {


    @ApiModelProperty(value = "岗位id,添加不传，修改需要")
    private Long id;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "公司id")
    private Integer cId;

    @ApiModelProperty(value = "排序")
    private Integer paixu;

    @ApiModelProperty(value = "0为启用1为停用")
    private Integer isOn;




}
