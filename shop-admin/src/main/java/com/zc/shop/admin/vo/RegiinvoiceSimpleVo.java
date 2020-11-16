package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value="用在发票查询返回对象中")
@Data
public class RegiinvoiceSimpleVo {


    private String orderCode;

    private Goods goods;

    private Settlement settlement;


}
