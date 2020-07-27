package com.zc.shop.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(description="查询订单的时候返回给前端的信息,包含完整信息")
public class OrdersAllVo {

    //订单部分
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "供应商id")
    private Integer orderSupplierId;

    @ApiModelProperty(value = "商品id")
    private Integer orderGoodsId;

    @ApiModelProperty(value = "购买数量")
    private Integer orderNum;

    @ApiModelProperty(value = "重量")
    private BigDecimal orderWeight;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "买家id")
    private Integer orderBuyId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCreatedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderUpdatedAt;

    @ApiModelProperty(value = "订单余量")
    private BigDecimal orderRemaining;

    @ApiModelProperty(value = "订单留言，备用")
    private String orderRemark;


    //商品部分

    @ApiModelProperty(value = "关联节点Attribute表用于分类展示")
    private Short goodsCatId;

    @ApiModelProperty(value = "商品货号")
    private String goodsGoodsSn;

    @ApiModelProperty(value = "商品品名")
    private String goodsGoodsName;

    @ApiModelProperty(value = "供应商计划销售数量")
    private Short goodsGoodsNumber;

    @ApiModelProperty(value = "商品重量")
    private BigDecimal goodsGoodsWeight;

    @ApiModelProperty(value = "商品数量单位，1为吨，2为千克，3为m3")
    private Short goodsWeightUnit;

    @ApiModelProperty(value = "商品本网售价")
    private BigDecimal goodsShopPrice;

    @ApiModelProperty(value = "商品缩略图,备用字段")
    private String goodsGoodsThumb;

    @ApiModelProperty(value = "商品备用字段")
    private String goodsGoodsImg;

    @ApiModelProperty(value = "商品原始图片地址,备用字段")
    private String goodsOriginalImg;

    @ApiModelProperty(value = "商品 上架为1，下架为0")
    private Integer goodsIsOnSale;

    @ApiModelProperty(value = "1为免运费，0为正常运费,备用字段")
    private Integer goodsIsShipping;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime goodsAddTime;

    @ApiModelProperty(value = "商品地区")
    private String goodsAttr1;

    @ApiModelProperty(value = "商品提货地点")
    private String goodsAttr2;

    @ApiModelProperty(value = "商品供应商ID，关联suppliers表")
    private Short goodsSuppliersId;

    @ApiModelProperty(value = "合作仓储方，关联ware_user表")
    private Integer goodsWareId;

    @ApiModelProperty(value = "商品商标")
    private String goodsTrademark;

    @ApiModelProperty(value = "商品备注")
    private String goodsRemark;

  //品种品名表
  @ApiModelProperty(value = "品种")
  private String attrPingZhong;

   @ApiModelProperty(value = "品名")
   private String attrAttrName;

    @ApiModelProperty(value = "规格")
    private String attrSpecifications;

    @ApiModelProperty(value = "材质")
    private String attrMaterial;
//仓库表
   @ApiModelProperty(value = "仓库名称")
   private String wareTitle;

    @ApiModelProperty(value = "仓库联系人")
    private String wareName;

    @ApiModelProperty(value = "仓库联系电话")
    private Integer warePhone;

    @ApiModelProperty(value = "仓库城市")
    private String wareCity;

    @ApiModelProperty(value = "仓库地址")
    private String wareAddress;


    @ApiModelProperty(value = "仓库介绍")
    private String wareContent;
//用户表
    @ApiModelProperty(value = "用户名")
    private String userUsername;

    @ApiModelProperty(value = "用户昵称")
    private String userNickname;
//提单表
  /*  @ApiModelProperty(value = "提单id")
     private Long ladingId;

    @ApiModelProperty(value = "提单编号")
    private String ladingCode;

    @ApiModelProperty(value = "提货重量")
    private BigDecimal ladingWeight;

    @ApiModelProperty(value = "提单状态")
    private Integer ladingStatus;

    @ApiModelProperty(value = "提货时间")
    private LocalDateTime ladingLadingedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ladingCreatedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ladingUpdatedAt;

    @ApiModelProperty(value = "提单留言")
    private String ladingRemark;

    @ApiModelProperty(value = "提单图片")
    private String ladingPic;
*/






}
