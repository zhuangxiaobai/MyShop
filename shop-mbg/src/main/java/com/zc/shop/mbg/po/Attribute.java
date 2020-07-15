package com.zc.shop.mbg.po;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Attribute implements Serializable {
    @ApiModelProperty(value = "ID")
    private Short id;

    @ApiModelProperty(value = "品名")
    private String attrName;

    @ApiModelProperty(value = "规格")
    private String specifications;

    @ApiModelProperty(value = "材质")
    private String material;

    @ApiModelProperty(value = "对应父id")
    private Integer parentId;

    private static final long serialVersionUID = 1L;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attrName=").append(attrName);
        sb.append(", specifications=").append(specifications);
        sb.append(", material=").append(material);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}