package com.zc.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmployeesInsertParam {

    //添加不用，修改用
     private  Long id;

    @ApiModelProperty("雇员的登录账号(手机号),新增必须")
     private String phoneNumber;

     private String name;

     private Long departmentId;

     private Long positionId;

     @ApiModelProperty("雇员所在的公司中的角色id,新增必须")
     private Long roleId;

     private Integer gender;

     private Integer isOn;

     @ApiModelProperty("雇员所在的公司id,新增必须")
     private  Integer cId;




}
