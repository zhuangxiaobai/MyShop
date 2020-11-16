package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Regiinvoice implements Serializable {
    @ApiModelProperty(value = "发票登记ID")
    private Long id;

    @ApiModelProperty(value = "订单code")
    private String orderCode;

    @ApiModelProperty(value = "发票号")
    private String invoiceNumber;

    @ApiModelProperty(value = "开票日期")
    private LocalDateTime date;

    @ApiModelProperty(value = "快递公司")
    private String courier;

    @ApiModelProperty(value = "收票单位")
    private String danwei;

    @ApiModelProperty(value = "快递单号")
    private String courierCode;

    @ApiModelProperty(value = "发票寄货状态")
    private Integer status;

    @ApiModelProperty(value = "总开票重量")
    private BigDecimal makeWeight;

    @ApiModelProperty(value = "总开票金额")
    private BigDecimal makeMoney;

    @ApiModelProperty(value = "开票单位")
    private String kaidanwei;

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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getCourierCode() {
        return courierCode;
    }

    public void setCourierCode(String courierCode) {
        this.courierCode = courierCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMakeWeight() {
        return makeWeight;
    }

    public void setMakeWeight(BigDecimal makeWeight) {
        this.makeWeight = makeWeight;
    }

    public BigDecimal getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(BigDecimal makeMoney) {
        this.makeMoney = makeMoney;
    }

    public String getKaidanwei() {
        return kaidanwei;
    }

    public void setKaidanwei(String kaidanwei) {
        this.kaidanwei = kaidanwei;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", invoiceNumber=").append(invoiceNumber);
        sb.append(", date=").append(date);
        sb.append(", courier=").append(courier);
        sb.append(", danwei=").append(danwei);
        sb.append(", courierCode=").append(courierCode);
        sb.append(", status=").append(status);
        sb.append(", makeWeight=").append(makeWeight);
        sb.append(", makeMoney=").append(makeMoney);
        sb.append(", kaidanwei=").append(kaidanwei);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}