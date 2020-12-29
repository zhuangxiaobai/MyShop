package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zc.shop.admin.dto.UpdatePersonalParam;
import com.zc.shop.admin.dto.UpdateUserMailParam;
import com.zc.shop.admin.dto.UpdateUserNameParam;
import com.zc.shop.admin.dto.UsersParam;
import com.zc.shop.admin.mapper.UsersExtMapper;
import com.zc.shop.admin.service.UserService;
import com.zc.shop.admin.util.JwtTokenUtil;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsersExtMapper usersExtMapper;


    @Override
    @Transactional(rollbackFor =  Exception.class)
    public Users register(UsersParam usersParam) {

        String username = usersParam.getUsername();

        String nickname = usersParam.getNickname();


        //去数据库查一下看是否有重复的用户名
        Users users = usersExtMapper.selectUserByUserName(username);
        if(users != null){
            throw new BusinessException(ResultCode.USERNAMEEXIST);
        }


        //去数据库查一下看是否有重复的昵称
        Users users2 = usersExtMapper.selectUserByNickName(nickname);
        if(users2 != null){
            throw new BusinessException(ResultCode.NICKNAMEEXIST);
        }

        //允许注册
        Users userToReg = new Users();
        BeanUtils.copyProperties(usersParam, userToReg);
        //md5加密
        String md5Password = DigestUtil.md5Hex(userToReg.getPassword());
        userToReg.setPassword(md5Password);
        userToReg.setCreatedAt(LocalDateTime.now());
        userToReg.setUpdatedAt(LocalDateTime.now());

        //添加时时候会判断时候为null，null字段不插入
        usersExtMapper.insertSelective(userToReg);


        return userToReg;
    }

    @Override
    public String login(String username, String password) {
        //密码加密
        password = DigestUtil.md5Hex(password);
        //去数据库查询一下看用户名和密码是否存在
        Users user = usersExtMapper.selectUserByUserNameAndPassword(username,password);

        //没有就抛出异常
        if(user == null){
            throw new BusinessException(ResultCode.USERNAMEORPASSWORDERROR);
        }

        //登陆成功创建token
        String token = jwtTokenUtil.generateToken(user);
        System.out.println("生成的token:"+token);

        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public int updatePassword(Users currenetUser, String newPassword) {

        currenetUser.setPassword(newPassword);
        currenetUser.setUpdatedAt(LocalDateTime.now());


        return usersExtMapper.updateByPrimaryKeySelective(currenetUser);
    }

    @Override
    public Users userExistByUserName(String username) {


        return usersExtMapper.selectUserByUserName(username);

    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public int resetPassWord(String mobile, String newPassword) {

         //查一下用户名是否存在
        Users users = usersExtMapper.selectUserByUserName(mobile);
        if(users == null){
            throw new BusinessException(ResultCode.USERNAMENOTEXIST);
        }
        //存在进行修改
        users.setPassword(newPassword);
        users.setUpdatedAt(LocalDateTime.now());


        return usersExtMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public int updatePersonal(UpdatePersonalParam updatePersonalParam) {

       Users users  = new Users();

        BeanUtil.copyProperties(updatePersonalParam,users);
       return usersExtMapper.updateByPrimaryKeySelective(users);




    }

    @Override
    public int updateMail(UpdateUserMailParam updateUserMailParam) {

        String md5Password = DigestUtil.md5Hex(updateUserMailParam.getPassword());

        Users user = usersExtMapper.selectUserByUserNameAndPassword(updateUserMailParam.getUserName(),md5Password);



        //都相等验证通过去修改
        if((user!=null)&& (user.getUsername().equals(updateUserMailParam.getUserName()))
                && (md5Password.equals(user.getPassword()))){


            user.setEmail(updateUserMailParam.getEmail());
            user.setUpdatedAt(LocalDateTime.now());


            int i = usersExtMapper.updateByPrimaryKeySelective(user);

            if(i!=1){
                throw new BusinessException(ResultCode.FAILED);
            }

            return i;

        }

        //否则返回
       throw new BusinessException(ResultCode.USERNAMEORPASSWORDERROR);
    }

    @Override
    public int updateUserName(UpdateUserNameParam updateUserNameParam) {


        String md5Password = DigestUtil.md5Hex(updateUserNameParam.getPassword());

        Users user = usersExtMapper.selectUserByUserNameAndPassword(updateUserNameParam.getUserName(),md5Password);



        //都相等验证通过去修改
        if((user!=null)&& (user.getUsername().equals(updateUserNameParam.getUserName()))
                && (md5Password.equals(user.getPassword()))){


            user.setUsername(updateUserNameParam.getNewUserName());
            user.setUpdatedAt(LocalDateTime.now());


            int i = usersExtMapper.updateByPrimaryKeySelective(user);

            if(i!=1){
                throw new BusinessException(ResultCode.FAILED);
            }

            return i;

        }

        //否则返回
        throw new BusinessException(ResultCode.USERNAMEORPASSWORDERROR);


    }
}
