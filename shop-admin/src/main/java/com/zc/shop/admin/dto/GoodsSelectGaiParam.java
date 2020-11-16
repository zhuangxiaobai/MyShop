package com.zc.shop.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoodsSelectGaiParam {


    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品品名")
    private List<String> goodsNameList;

    @ApiModelProperty(value = "材质")
    private List<String> caizhiList;

    @ApiModelProperty(value = "规格")
    private List<String> guigeList;

    @ApiModelProperty(value = "交货地")
    private List<String> addressList;

    @ApiModelProperty(value = "钢厂/厂家")
    private List<String> factoryList;

    @ApiModelProperty(value = "单位/公司")
    private String danwei;

    @ApiModelProperty(value = "1为预售2为期货3为定向，前端操作默认传2")
    private Integer isOnSale;

    @ApiModelProperty(value = "分页对象")
    private PageParam pageParam;

}
