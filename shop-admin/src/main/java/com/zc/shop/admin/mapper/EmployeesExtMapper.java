package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.EmployeesSelectParam;
import com.zc.shop.admin.vo.EmployeesVo;
import com.zc.shop.mbg.mapper.EmployeesMapper;

import java.util.List;

public interface EmployeesExtMapper extends EmployeesMapper {
    List<EmployeesVo> selectEmployeesVoList(EmployeesSelectParam employeesSelectParam);

    int selectEmployeesVoListNum(EmployeesSelectParam employeesSelectParam);
}
