package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DepartmentInsertParam {



    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "上级部门id,如是顶级部门则不传此字段")
    private Integer parentId;

    @ApiModelProperty(value = "公司id")
    private Integer cId;

    @ApiModelProperty(value = "排序")
    private Integer paixu;

    @ApiModelProperty(value = "0为启用1为停用")
    private Integer isOn;





}
