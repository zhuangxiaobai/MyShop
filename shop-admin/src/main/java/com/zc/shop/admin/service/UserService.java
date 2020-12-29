package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.UpdatePersonalParam;
import com.zc.shop.admin.dto.UpdateUserMailParam;
import com.zc.shop.admin.dto.UpdateUserNameParam;
import com.zc.shop.admin.dto.UsersParam;
import com.zc.shop.mbg.po.Users;

public interface UserService {
    Users register(UsersParam usersParam);

    String login(String username, String password);

    String refreshToken(String token);

    int updatePassword(Users currenetUser, String newPassword);

    Users userExistByUserName(String username);

    int resetPassWord(String mobile, String newPassword);

    int updatePersonal(UpdatePersonalParam updatePersonalParam);

    int updateMail(UpdateUserMailParam updateUserMailParam);

    int updateUserName(UpdateUserNameParam updateUserNameParam);
}
