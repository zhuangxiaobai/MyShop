package com.zc.shop.mbg.po;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {
    @ApiModelProperty(value = "商品表主ID")
    private Integer id;

    @ApiModelProperty(value = "关联节点category表用于分类展示")
    private Short catId;

    @ApiModelProperty(value = "商品货号，SZ+即将插入产生的6位goods_id，不足6位于SZ和goods_id间补0，如SZ003329")
    private String goodsSn;

    @ApiModelProperty(value = "商品品名，对应atribute表品名")
    private String goodsName;

    @ApiModelProperty(value = "供应商计划销售数量")
    private Short goodsNumber;

    @ApiModelProperty(value = "修正重量显示用，统一赋值1")
    private BigDecimal goodsWeight;

    @ApiModelProperty(value = "商品数量单位，1为吨，2为千克，3为m3")
    private Short weightUnit;

    @ApiModelProperty(value = "本网售价")
    private BigDecimal shopPrice;

    @ApiModelProperty(value = "商品缩略图")
    private String goodsThumb;

    private String goodsImg;

    @ApiModelProperty(value = "原始图片地址")
    private String originalImg;

    @ApiModelProperty(value = "上架为1，下架为0")
    private Boolean isOnSale;

    @ApiModelProperty(value = "1为免运费，0为正常运费")
    private Boolean isShipping;

    @ApiModelProperty(value = "商品发布时间时间戳")
    private Integer addTime;

    @ApiModelProperty(value = "地区")
    private String attr1;

    @ApiModelProperty(value = "提货地点")
    private String attr2;

    @ApiModelProperty(value = "商品供应商ID，关联suppliers表")
    private Short suppliersId;

    @ApiModelProperty(value = "合作仓储方，关联ware_user表")
    private Integer wareId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCatId() {
        return catId;
    }

    public void setCatId(Short catId) {
        this.catId = catId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Short getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Short goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Short getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(Short weightUnit) {
        this.weightUnit = weightUnit;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Boolean getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Boolean isShipping) {
        this.isShipping = isShipping;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public Short getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Short suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catId=").append(catId);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsNumber=").append(goodsNumber);
        sb.append(", goodsWeight=").append(goodsWeight);
        sb.append(", weightUnit=").append(weightUnit);
        sb.append(", shopPrice=").append(shopPrice);
        sb.append(", goodsThumb=").append(goodsThumb);
        sb.append(", goodsImg=").append(goodsImg);
        sb.append(", originalImg=").append(originalImg);
        sb.append(", isOnSale=").append(isOnSale);
        sb.append(", isShipping=").append(isShipping);
        sb.append(", addTime=").append(addTime);
        sb.append(", attr1=").append(attr1);
        sb.append(", attr2=").append(attr2);
        sb.append(", suppliersId=").append(suppliersId);
        sb.append(", wareId=").append(wareId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}