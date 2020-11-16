package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class Statements implements Serializable {
    @ApiModelProperty(value = "结算表ID")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "订单购买总数量")
    private Integer buyNumber;

    @ApiModelProperty(value = "订单购买总重量")
    private BigDecimal buyWeight;

    @ApiModelProperty(value = "实提总数量")
    private Integer landNumber;

    @ApiModelProperty(value = "实提总重量")
    private BigDecimal landWeight;

    @ApiModelProperty(value = "差值总数量")
    private Integer diffNumber;

    @ApiModelProperty(value = "差值总重量")
    private BigDecimal diffWeight;

    @ApiModelProperty(value = "结算总金额")
    private BigDecimal money;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getBuyWeight() {
        return buyWeight;
    }

    public void setBuyWeight(BigDecimal buyWeight) {
        this.buyWeight = buyWeight;
    }

    public Integer getLandNumber() {
        return landNumber;
    }

    public void setLandNumber(Integer landNumber) {
        this.landNumber = landNumber;
    }

    public BigDecimal getLandWeight() {
        return landWeight;
    }

    public void setLandWeight(BigDecimal landWeight) {
        this.landWeight = landWeight;
    }

    public Integer getDiffNumber() {
        return diffNumber;
    }

    public void setDiffNumber(Integer diffNumber) {
        this.diffNumber = diffNumber;
    }

    public BigDecimal getDiffWeight() {
        return diffWeight;
    }

    public void setDiffWeight(BigDecimal diffWeight) {
        this.diffWeight = diffWeight;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", buyNumber=").append(buyNumber);
        sb.append(", buyWeight=").append(buyWeight);
        sb.append(", landNumber=").append(landNumber);
        sb.append(", landWeight=").append(landWeight);
        sb.append(", diffNumber=").append(diffNumber);
        sb.append(", diffWeight=").append(diffWeight);
        sb.append(", money=").append(money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}