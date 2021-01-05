package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    @ApiModelProperty(value = "自增id")
    private Integer id;

    @ApiModelProperty(value = "消息类型,0是代办,1是私信(占时没用),2是系统")
    private Integer type;

    @ApiModelProperty(value = "消息类型细分,0为买家,1为卖家，可为null")
    private Integer typeNext;

    @ApiModelProperty(value = "信息创建人id,不为null,系统的话为0")
    private Integer createId;

    @ApiModelProperty(value = "信息接受者id,必须有值，当为0且type为2时代表系统向全员发消息")
    private Integer receiveId;

    @ApiModelProperty(value = "消息信息id,关联info表")
    private Integer infoId;

    @ApiModelProperty(value = "0未读,1已读")
    private Integer status;

    @ApiModelProperty(value = "是否删除,0没删,1删了")
    private Integer isDelete;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeNext() {
        return typeNext;
    }

    public void setTypeNext(Integer typeNext) {
        this.typeNext = typeNext;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", typeNext=").append(typeNext);
        sb.append(", createId=").append(createId);
        sb.append(", receiveId=").append(receiveId);
        sb.append(", infoId=").append(infoId);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}