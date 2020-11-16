package com.zc.shop.admin.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.zc.shop.mbg.po.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description="买家提货管理，获取的提单信息")
public class LadingAllVo {


    private Lading lading;

    private Certification certification;

    private Order order;

    private Goods goods;




}
