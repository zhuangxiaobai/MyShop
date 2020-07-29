package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSelectAttrChangeParam {
    @ApiModelProperty(value = "品种")
    private String pingZhong;
    @ApiModelProperty(value = "品名集合")
    private List<String> attrNameList;
}
