package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.UsersMapper;
import com.zc.shop.mbg.po.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersExtMapper extends UsersMapper {


         Users selectUserByUserName(@Param("username") String username);






}
