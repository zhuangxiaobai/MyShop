package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Goods implements Serializable {
    @ApiModelProperty(value = "商品表主ID")
    private Integer id;

    @ApiModelProperty(value = "资源号")
    private String goodsSn;

    @ApiModelProperty(value = "商品品名")
    private String goodsName;

    @ApiModelProperty(value = "供应商计划销售数量")
    private Short goodsNumber;

    @ApiModelProperty(value = "商品总重")
    private BigDecimal goodsWeight;

    @ApiModelProperty(value = "商品数量单位，1为吨，2为千克，3为m3")
    private Short weightUnit;

    @ApiModelProperty(value = "本网售价")
    private BigDecimal shopPrice;

    @ApiModelProperty(value = "1为预售2为期货3为定向")
    private Integer isOnSale;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "交货")
    private String address;

    @ApiModelProperty(value = "商品供应商ID，关联suppliers表")
    private Short suppliersId;

    @ApiModelProperty(value = "仓库")
    private String warehouse;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "0过磅1理计2抄码")
    private Integer weitype;

    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "规格")
    private String guige;

    @ApiModelProperty(value = "材质")
    private String caizhi;

    @ApiModelProperty(value = "钢厂")
    private String factory;

    @ApiModelProperty(value = "单位")
    private String danwei;

    @ApiModelProperty(value = "0为上架1为下架")
    private Integer isDelete;

    @ApiModelProperty(value = "剩余数量")
    private Short remainNumber;

    @ApiModelProperty(value = "剩余重量")
    private BigDecimal remainWeight;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Short getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Short suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getWeitype() {
        return weitype;
    }

    public void setWeitype(Integer weitype) {
        this.weitype = weitype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Short getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(Short remainNumber) {
        this.remainNumber = remainNumber;
    }

    public BigDecimal getRemainWeight() {
        return remainWeight;
    }

    public void setRemainWeight(BigDecimal remainWeight) {
        this.remainWeight = remainWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsNumber=").append(goodsNumber);
        sb.append(", goodsWeight=").append(goodsWeight);
        sb.append(", weightUnit=").append(weightUnit);
        sb.append(", shopPrice=").append(shopPrice);
        sb.append(", isOnSale=").append(isOnSale);
        sb.append(", addTime=").append(addTime);
        sb.append(", address=").append(address);
        sb.append(", suppliersId=").append(suppliersId);
        sb.append(", warehouse=").append(warehouse);
        sb.append(", remark=").append(remark);
        sb.append(", weitype=").append(weitype);
        sb.append(", type=").append(type);
        sb.append(", guige=").append(guige);
        sb.append(", caizhi=").append(caizhi);
        sb.append(", factory=").append(factory);
        sb.append(", danwei=").append(danwei);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", remainNumber=").append(remainNumber);
        sb.append(", remainWeight=").append(remainWeight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}