package com.zc.shop.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttributeVo {


    @ApiModelProperty(value = "品种")
    private String attrPingZhong;

    @ApiModelProperty(value = "品名")
    private String attrAttrName;

    @ApiModelProperty(value = "规格")
    private String attrSpecifications;

    @ApiModelProperty(value = "材质")
    private String attrMaterial;

}
