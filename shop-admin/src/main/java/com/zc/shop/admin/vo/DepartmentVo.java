package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Department;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentVo {


    private String label;

    private Department department;



    private List<DepartmentVo> departmentVoList;




}
