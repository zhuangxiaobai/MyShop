package com.zc.shop.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.UserService;
import com.zc.shop.admin.util.MyCacheUtil;
import org.apache.commons.lang3.StringUtils;
import com.zc.shop.admin.vo.UsersVo;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(tags = "UserController", description = "用户")
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Value("${uploadFile.location}")
    private String uploadFileLocation;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值
    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UsersVo> register(@RequestBody @ApiParam(value="传入用户注册对象")UsersParam usersParam) {
        //验证手机短信验证码是否正确
        String yanzhengma = MyCacheUtil.get(usersParam.getUsername());
        if(yanzhengma==null || !yanzhengma.equals(usersParam.getCaptcha())){
            return CommonResult.failed(ResultCode.CAPTCHADERROR);
        }

        Users user = userService.register(usersParam);
        if (user == null) {
            CommonResult.failed();
        }

        UsersVo usersVo = new UsersVo();
        BeanUtil.copyProperties(user,usersVo);
        return CommonResult.success(usersVo);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody @ApiParam(value="传入用户登录对象")UsersLoginParam usersLoginParam ) {
        String token = userService.login(usersLoginParam.getUsername(), usersLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {

        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed(ResultCode.UNAUTHORIZED);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }


    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UsersVo> getUserInfo(HttpServletRequest request) {

        Users currenetUser = (Users) request.getAttribute("user");
        if(currenetUser == null){
              throw new BusinessException(ResultCode.LOGINUSERISNULL);
          }
        UsersVo usersVo = new UsersVo();

        BeanUtil.copyProperties(currenetUser,usersVo);
        return CommonResult.success(usersVo);
    }



    @ApiOperation(value = "修改密码，原密码改成新密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestBody @ApiParam(value="修改用户密码")UpdateUsersPasswordParam updateUsersPasswordParam, HttpServletRequest request) {
        String oldPassword = updateUsersPasswordParam.getPassword();
        String newPassword = updateUsersPasswordParam.getNewPassword();
        String oldPasswordToMd5 = DigestUtil.md5Hex(oldPassword);
        String newPasswordToMd5 = DigestUtil.md5Hex(newPassword);
        //验证老密码是否正确
        Users currenetUser = (Users) request.getAttribute("user");
        if(!currenetUser.getPassword().equals(oldPasswordToMd5)){
            throw new BusinessException(ResultCode.PASSWORDERROR);
        }

        //传入新密码去修改
        userService.updatePassword(currenetUser,newPasswordToMd5);


        return CommonResult.success(null);
    }

    @ApiOperation(value = "忘记密码，需要用手机验证码重设")
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult forgetPassword(@Validated @RequestBody @ApiParam(value="重置用户密码")ForgetUsersPasswordParam forgetUsersPasswordParam, HttpServletRequest request) {

        String mobile = forgetUsersPasswordParam.getMobile();
        String captcha =forgetUsersPasswordParam.getCaptcha();
        String newPassword =forgetUsersPasswordParam.getNewPassword();
        String newPasswordToMd5 = DigestUtil.md5Hex(newPassword);
        //验证一下验证码是否正确
        //验证手机短信验证码是否正确
        String yanzhengma = MyCacheUtil.get(mobile);
        if(!yanzhengma.equals(captcha)){
            return CommonResult.failed(ResultCode.CAPTCHADERROR);
        }

        //验证成功,去修改密码

         userService.resetPassWord(mobile,newPasswordToMd5);




        return CommonResult.success(null);
    }




//    @ApiOperation(value = "获取当前登录的用户信息")
//    @RequestMapping(value = "/info", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult<UsersVo> register(HttpServletRequest request) {
//
//          Users user = (Users)request.getAttribute("user");
//          if(user == null){
//              throw new BusinessException(ResultCode.LOGINUSERISNULL);
//          }
//
//          UsersVo usersVo = new UsersVo();
//          BeanUtil.copyProperties(user,usersVo);
//
//
//         return CommonResult.success(usersVo);
//
//
//    }















    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sendMessage(@Validated @RequestBody @ApiParam(value="获取手机验证码") MobileSendMessageParam mobileSendMessageParam) {
        String username = mobileSendMessageParam.getMobile();
        String mobileType = mobileSendMessageParam.getType();

            //注册
        if(mobileType.equals("1")){

            //查询一下手机号是否已经注册
            Users users = userService.userExistByUserName(username);
            if(users != null){
                return CommonResult.failed(ResultCode.USERNAMEEXIST);
            }
            //重置密码
        }else if(mobileType.equals("2")){

            //查询一下手机号是否已经注册
            Users users = userService.userExistByUserName(username);
            if(users == null){
                return CommonResult.failed(ResultCode.USERNAMENOTEXIST);
            }


           //发其他的提示失败
        }else{

            return  CommonResult.failed(ResultCode.PARAMNOTFULL);
        }


       //没注册发验证码
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G1tUujv578myWMzCgCt", "qVGipmNyZ9grpNyjjiR7IayteOEMfq");
        IAcsClient client = new DefaultAcsClient(profile);

        //生成4位随机数,random为左闭右开的
        String  yanzhengma = String.valueOf((int)((Math.random()*9+1)*1000));

        System.out.println("验证码为"+yanzhengma);
        //把验证码存入定时缓存缓存中,时间为5分钟,key为手机号，value为验证码
        MyCacheUtil.put(mobileSendMessageParam.getMobile(),yanzhengma);


        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobileSendMessageParam.getMobile());
        request.putQueryParameter("SignName", "精益管理");
        request.putQueryParameter("TemplateCode", "SMS_197871345");
        request.putQueryParameter("TemplateParam", "{'code':"+yanzhengma+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

            //通过gson吧string转成map
             Gson gson = new Gson();
             Map<String, String> map = new HashMap<String, String>();
             map = gson.fromJson(response.getData(), map.getClass());
             String message =  map.get("Message");
             String code= map.get("Code");
            if("OK".equals(code)){
                return CommonResult.success(message);
             }else{
               return CommonResult.failed(message);
            }


        }catch (ServerException e) {
            e.printStackTrace();
            return CommonResult.failed();
        } catch (ClientException e) {
            e.printStackTrace();
            return CommonResult.failed();
        }




    }





    @ApiOperation("上传图片接口")
    @RequestMapping(value = "/uploadImage",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadImage(@RequestParam(value = "files" ) MultipartFile[] files,
                                    HttpServletRequest request) {

        List<String> list = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //测试
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        // 数组转String字符串
        String newStr = StringUtils.join(list, ",");
        System.out.println(newStr);


        return CommonResult.success(newStr);
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                // String filePath = "C:/fileUpload/picture" + (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename());

                String fileName = "picture"+(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename());
                String filePath=  uploadFileLocation + fileName;



                list.add(filePath);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists()) {
                    saveDir.getParentFile().mkdirs();
                }
                // 转存文件
                file.transferTo(saveDir);


                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }







}
