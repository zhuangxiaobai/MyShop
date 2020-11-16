package com.zc.shop.admin.mapper;

import com.zc.shop.admin.vo.DepartmentVo;
import com.zc.shop.mbg.mapper.DepartmentMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentExtMapper extends DepartmentMapper {
    //List<DepartmentVo> selectDepartmentMenu(@Param("c_id") Integer c_id);

    List<DepartmentVo> selectMenuTree(@Param("c_id")Integer c_id);
}
