package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleInsertParam {


    @ApiModelProperty(value = "角色id,添加不传，修改需要")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "公司id")
    private Integer cId;

    @ApiModelProperty(value = "角色级别")
    private Integer level;

    @ApiModelProperty(value = "角色权限(json集合)")
    private String permission;


    @ApiModelProperty(value = "描述")
    private String remark;




}
