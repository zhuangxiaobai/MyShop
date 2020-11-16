package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.RoleSelectParam;
import com.zc.shop.mbg.mapper.RolesMapper;
import com.zc.shop.mbg.po.Roles;

import java.util.List;

public interface RolesExtMapper extends RolesMapper {
    List<Roles> selectRolesList(RoleSelectParam roleSelectParam);

    int selectRolesListNum(RoleSelectParam roleSelectParam);
}
