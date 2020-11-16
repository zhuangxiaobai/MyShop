package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Storeinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class StoreinfoListVo {



    @ApiModelProperty(value = "当页集合数据")
    private List<Storeinfo> storeinfos;



    @ApiModelProperty(value = "数据总条数")
    private int total;

}
