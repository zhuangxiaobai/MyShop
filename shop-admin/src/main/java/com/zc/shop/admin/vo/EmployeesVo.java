package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.*;
import lombok.Data;

@Data
public class EmployeesVo {


   private UsersVo usersVo;

   private Employees employees;

   private Certification certification;

   private Department department;

   private Position position;

   private Roles roles;

}
