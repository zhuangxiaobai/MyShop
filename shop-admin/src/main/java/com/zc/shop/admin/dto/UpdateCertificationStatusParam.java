package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateCertificationStatusParam {

    @ApiModelProperty("需要修改的公司id")
    private Integer id;


    @ApiModelProperty("需要修改成的公司状态 1通过2不通过")
    private Integer status;

}
