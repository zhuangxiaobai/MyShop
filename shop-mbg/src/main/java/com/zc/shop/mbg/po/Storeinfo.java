package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Storeinfo implements Serializable {
    private Short id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "店铺名称")
    private String title;

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private Integer phone;

    @ApiModelProperty(value = "经营范围，主营")
    private String business;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地址，不用")
    private String address;

    @ApiModelProperty(value = "logo")
    private String logo;

    @ApiModelProperty(value = "最大销售重量")
    private BigDecimal maxSellweight;

    @ApiModelProperty(value = "最大提货重量")
    private BigDecimal maxLandweight;

    @ApiModelProperty(value = "最大销售数量")
    private Integer maxSellnum;

    @ApiModelProperty(value = "最大提货数量")
    private Integer maxLandnum;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "省份")
    private String provinces;

    @ApiModelProperty(value = "店铺介绍")
    private String content;

    private static final long serialVersionUID = 1L;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BigDecimal getMaxSellweight() {
        return maxSellweight;
    }

    public void setMaxSellweight(BigDecimal maxSellweight) {
        this.maxSellweight = maxSellweight;
    }

    public BigDecimal getMaxLandweight() {
        return maxLandweight;
    }

    public void setMaxLandweight(BigDecimal maxLandweight) {
        this.maxLandweight = maxLandweight;
    }

    public Integer getMaxSellnum() {
        return maxSellnum;
    }

    public void setMaxSellnum(Integer maxSellnum) {
        this.maxSellnum = maxSellnum;
    }

    public Integer getMaxLandnum() {
        return maxLandnum;
    }

    public void setMaxLandnum(Integer maxLandnum) {
        this.maxLandnum = maxLandnum;
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

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", business=").append(business);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", logo=").append(logo);
        sb.append(", maxSellweight=").append(maxSellweight);
        sb.append(", maxLandweight=").append(maxLandweight);
        sb.append(", maxSellnum=").append(maxSellnum);
        sb.append(", maxLandnum=").append(maxLandnum);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", provinces=").append(provinces);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}