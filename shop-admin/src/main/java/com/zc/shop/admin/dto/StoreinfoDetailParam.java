package com.zc.shop.admin.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreinfoDetailParam {



    @ApiModelProperty(value = "店铺id")
    private Short  storeinfoId;

    @ApiModelProperty(value = "品名")
    private String attrName;

    @ApiModelProperty(value = "地区,钢厂/产地")
    private String attr1;

    @ApiModelProperty(value = "合作仓储方，关联ware_user表，仓库/港口")
    private Integer wareId;


    @ApiModelProperty(value = "是否上架(有货)，1上架 0下架")
    private Integer isOnSale;

    @ApiModelProperty(value = "不传为null默认排序,1价格正排，2价格倒排，3上架时间正排，4上架时间倒排")
    private Integer orderBy;

    @ApiModelProperty(value = "分页对象")
    private PageParam pageParam;


}
