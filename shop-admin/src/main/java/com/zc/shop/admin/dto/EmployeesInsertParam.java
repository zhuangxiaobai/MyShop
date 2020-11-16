package com.zc.shop.admin.dto;

import lombok.Data;

@Data
public class EmployeesInsertParam {

    //添加不用，修改用
     private  Long id;

     private String phoneNumber;

     private String name;

     private Long departmentId;

     private Long positionId;

     private Long roleId;

     private Integer gender;

     private Integer isOn;

    private  Integer cId;




}
