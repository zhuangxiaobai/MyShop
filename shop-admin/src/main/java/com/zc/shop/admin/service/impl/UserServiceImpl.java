package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.UsersParam;
import com.zc.shop.admin.service.UserService;
import com.zc.shop.admin.util.JwtTokenUtil;
import com.zc.shop.mbg.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public Users register(UsersParam usersParam) {
        //去数据库查一下看是否有重复(用户名，昵称)






        return null;
    }

    @Override
    public String login(String username, String password) {
        //去数据库查询一下看是否是用户







        return null;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }
}
