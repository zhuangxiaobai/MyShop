package com.zc.shop.mbg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Certification implements Serializable {
    @ApiModelProperty(value = "认证表主ID")
    private Integer id;

    @ApiModelProperty(value = "公司名称")
    private String company;

    @ApiModelProperty(value = "社会信用代码")
    private String xinyong;

    @ApiModelProperty(value = "法人")
    private String faren;

    @ApiModelProperty(value = "公司类型")
    private String companytype;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime builddate;

    @ApiModelProperty(value = "注册资本")
    private String ziben;

    @ApiModelProperty(value = "营业期限")
    private String qixian;

    @ApiModelProperty(value = "法人身份证")
    private String idcard;

    @ApiModelProperty(value = "住所")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String lianxiren;

    @ApiModelProperty(value = "联系人身份证")
    private String lxridcard;

    @ApiModelProperty(value = "申请说明")
    private String remark;

    @ApiModelProperty(value = "开票银行")
    private String back;

    @ApiModelProperty(value = "银行账号")
    private String account;

    @ApiModelProperty(value = "税号")
    private String shuihao;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "公司地址")
    private String companyaddress;

    @ApiModelProperty(value = "三证合一1是0否")
    private Integer isSanzheng;

    @ApiModelProperty(value = "0待审核1通过2不通过")
    private Integer status;

    @ApiModelProperty(value = "userid")
    private Integer userid;

    @ApiModelProperty(value = "营业执照")
    private String yinyezhizhao;

    @ApiModelProperty(value = "授权书")
    private String shouquanshu;

    @ApiModelProperty(value = "开票资料")
    private String kaipiaoziliao;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getXinyong() {
        return xinyong;
    }

    public void setXinyong(String xinyong) {
        this.xinyong = xinyong;
    }

    public String getFaren() {
        return faren;
    }

    public void setFaren(String faren) {
        this.faren = faren;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public LocalDateTime getBuilddate() {
        return builddate;
    }

    public void setBuilddate(LocalDateTime builddate) {
        this.builddate = builddate;
    }

    public String getZiben() {
        return ziben;
    }

    public void setZiben(String ziben) {
        this.ziben = ziben;
    }

    public String getQixian() {
        return qixian;
    }

    public void setQixian(String qixian) {
        this.qixian = qixian;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLianxiren() {
        return lianxiren;
    }

    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren;
    }

    public String getLxridcard() {
        return lxridcard;
    }

    public void setLxridcard(String lxridcard) {
        this.lxridcard = lxridcard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getShuihao() {
        return shuihao;
    }

    public void setShuihao(String shuihao) {
        this.shuihao = shuihao;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public Integer getIsSanzheng() {
        return isSanzheng;
    }

    public void setIsSanzheng(Integer isSanzheng) {
        this.isSanzheng = isSanzheng;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getYinyezhizhao() {
        return yinyezhizhao;
    }

    public void setYinyezhizhao(String yinyezhizhao) {
        this.yinyezhizhao = yinyezhizhao;
    }

    public String getShouquanshu() {
        return shouquanshu;
    }

    public void setShouquanshu(String shouquanshu) {
        this.shouquanshu = shouquanshu;
    }

    public String getKaipiaoziliao() {
        return kaipiaoziliao;
    }

    public void setKaipiaoziliao(String kaipiaoziliao) {
        this.kaipiaoziliao = kaipiaoziliao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company=").append(company);
        sb.append(", xinyong=").append(xinyong);
        sb.append(", faren=").append(faren);
        sb.append(", companytype=").append(companytype);
        sb.append(", builddate=").append(builddate);
        sb.append(", ziben=").append(ziben);
        sb.append(", qixian=").append(qixian);
        sb.append(", idcard=").append(idcard);
        sb.append(", address=").append(address);
        sb.append(", lianxiren=").append(lianxiren);
        sb.append(", lxridcard=").append(lxridcard);
        sb.append(", remark=").append(remark);
        sb.append(", back=").append(back);
        sb.append(", account=").append(account);
        sb.append(", shuihao=").append(shuihao);
        sb.append(", phone=").append(phone);
        sb.append(", companyaddress=").append(companyaddress);
        sb.append(", isSanzheng=").append(isSanzheng);
        sb.append(", status=").append(status);
        sb.append(", userid=").append(userid);
        sb.append(", yinyezhizhao=").append(yinyezhizhao);
        sb.append(", shouquanshu=").append(shouquanshu);
        sb.append(", kaipiaoziliao=").append(kaipiaoziliao);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}