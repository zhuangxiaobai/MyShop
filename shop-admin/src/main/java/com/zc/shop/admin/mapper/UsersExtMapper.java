package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.UsersMapper;
import com.zc.shop.mbg.po.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersExtMapper extends UsersMapper {


         Users selectUserByUserName(@Param("username") String username);


         Users selectUserByNickName(@Param("nickname")String nickname);

         Users selectUserByUserNameAndPassword(@Param("username") String username,@Param("password") String password);


         //int insertRegisterUser(Users userToReg);
}
