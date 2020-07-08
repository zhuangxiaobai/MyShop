package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.UsersParam;
import com.zc.shop.mbg.po.Users;

public interface UserService {
    Users register(UsersParam usersParam);

    String login(String username, String password);

    String refreshToken(String token);

    int updatePassword(Users currenetUser, String newPassword);
}
