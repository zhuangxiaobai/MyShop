package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;

import java.util.Map;

public interface EmployeesService {
    int createDepartmrnt(DepartmentInsertParam departmentInsertParam);

    int updateDepartmrnt(DepartmentInsertParam departmentInsertParam);

    Map departmentList(Integer c_id);

    int insertPosition(PositionInsertParam positionInsertParam);

    int updatePosition(PositionInsertParam positionInsertParam);

    Map positionList(PositionSelectParam positionSelectParam);

    int deletePosition(Long id);

    int insertRole(RoleInsertParam roleInsertParam);

    int updateRole(RoleInsertParam roleInsertParam);

    int deleteRole(Long id);

    Map roleList(RoleSelectParam roleSelectParam);

    Map pagesList();

    int inviteEmployee(EmployeesInsertParam employeesInsertParam);

    int updateEmployee(EmployeesInsertParam employeesInsertParam);

    int deleteEmployee(Long id);

    Map employeeList(EmployeesSelectParam employeesSelectParam);

    Map myPagesList(Integer userId);
}
