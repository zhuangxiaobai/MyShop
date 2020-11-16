package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Lading implements Serializable {
    private Long id;

    @ApiModelProperty(value = "提单编号")
    private String code;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "供应商id")
    private Integer supplierId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "买家申请提货重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "提单状态")
    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ladingedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "对应订单id")
    private Integer orderId;

    @ApiModelProperty(value = "0为整单1为分批2为过户")
    private Integer type;

    @ApiModelProperty(value = "过户单位")
    private String guohu;

    @ApiModelProperty(value = "实提件数")
    private Integer realNum;

    @ApiModelProperty(value = "实提重量")
    private BigDecimal realWeight;

    @ApiModelProperty(value = "买家申请提单数量")
    private Integer num;

    @ApiModelProperty(value = "提单留言")
    private String remark;

    @ApiModelProperty(value = "提单图片")
    private String pic;

    @ApiModelProperty(value = "司机信息等json格式")
    private String information;

    @ApiModelProperty(value = "授权书图片地址")
    private String shouquanshu;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getLadingedAt() {
        return ladingedAt;
    }

    public void setLadingedAt(LocalDateTime ladingedAt) {
        this.ladingedAt = ladingedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGuohu() {
        return guohu;
    }

    public void setGuohu(String guohu) {
        this.guohu = guohu;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    public BigDecimal getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(BigDecimal realWeight) {
        this.realWeight = realWeight;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getShouquanshu() {
        return shouquanshu;
    }

    public void setShouquanshu(String shouquanshu) {
        this.shouquanshu = shouquanshu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", weight=").append(weight);
        sb.append(", status=").append(status);
        sb.append(", ladingedAt=").append(ladingedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", orderId=").append(orderId);
        sb.append(", type=").append(type);
        sb.append(", guohu=").append(guohu);
        sb.append(", realNum=").append(realNum);
        sb.append(", realWeight=").append(realWeight);
        sb.append(", num=").append(num);
        sb.append(", remark=").append(remark);
        sb.append(", pic=").append(pic);
        sb.append(", information=").append(information);
        sb.append(", shouquanshu=").append(shouquanshu);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}