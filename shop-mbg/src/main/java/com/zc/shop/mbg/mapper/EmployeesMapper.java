package com.zc.shop.mbg.mapper;

import com.zc.shop.mbg.po.Employees;

public interface EmployeesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employees record);

    int insertSelective(Employees record);

    Employees selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);
}