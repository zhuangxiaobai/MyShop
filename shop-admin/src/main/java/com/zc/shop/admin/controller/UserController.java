package com.zc.shop.admin.controller;


import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.bean.BeanUtil;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "UserController", description = "用户")
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UsersVo> register(@RequestBody @ApiParam(value="传入用户注册对象")UsersParam usersParam) {
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
        //验证老密码是否正确
        Users currenetUser = (Users) request.getAttribute("user");
        if(!currenetUser.getPassword().equals(oldPassword)){
            throw new BusinessException(ResultCode.PASSWORDERROR);
        }

        //传入新密码去修改
        userService.updatePassword(currenetUser,newPassword);


        return CommonResult.success(null);
    }

    @ApiOperation(value = "忘记密码，需要用手机验证码重设")
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult forgetPassword(@Validated @RequestBody @ApiParam(value="重置用户密码")ForgetUsersPasswordParam forgetUsersPasswordParam, HttpServletRequest request) {

        String mobile = forgetUsersPasswordParam.getMobile();
        String captcha =forgetUsersPasswordParam.getCaptcha();
        String newPassword =forgetUsersPasswordParam.getNewPassword();
        //验证一下验证码是否正确








        //验证成功,去修改密码








        return CommonResult.success(null);
    }




















    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sendMessage(@Validated @RequestBody MobileSendMessageParam mobileSendMessageParam) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FgVL9JFiWNBCwNUssip", "rvX9vOLcH5Ng7MRKqlNhM5wRFw9D6P");
        IAcsClient client = new DefaultAcsClient(profile);

        String  yanzhengma = String.valueOf((int)Math.floor(Math.random()*10000));
        System.out.println("验证码为"+yanzhengma);
        //把验证码存入定时缓存缓存中,时间为5分钟,key为手机号，value为验证码
        TimedCache<String, String> timedCache = CacheUtil.newTimedCache(300000);
        timedCache.put(mobileSendMessageParam.getMobile(),yanzhengma);



        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobileSendMessageParam.getMobile());
        request.putQueryParameter("SignName", "StoryForest");
        request.putQueryParameter("TemplateCode", "SMS_175544438");
        request.putQueryParameter("TemplateParam", "{'start':'2020-07-08 16:50','end':'2020-07-08 16:50','remark':"+yanzhengma+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

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






        return CommonResult.success(null);


//
//        try {
//            *//* 必要步骤：
//             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey
//             * 本示例采用从环境变量读取的方式，需要预先在环境变量中设置这两个值
//             * 您也可以直接在代码中写入密钥对，但需谨防泄露，不要将代码复制、上传或者分享给他人
//             * CAM 密钥查询：https://console.cloud.tencent.com/cam/capi*//*
//            Credential cred = new Credential("secretId", "secretKey");
//
//            // 实例化一个 http 选项，可选，无特殊需求时可以跳过
//            HttpProfile httpProfile = new HttpProfile();
//            // 设置代理
//            httpProfile.setProxyHost("host");
//            httpProfile.setProxyPort(port);
//            *//* SDK 默认使用 POST 方法。
//             * 如需使用 GET 方法，可以在此处设置，但 GET 方法无法处理较大的请求 *//*
//            httpProfile.setReqMethod("POST");
//            *//* SDK 有默认的超时时间，非必要请不要进行调整
//             * 如有需要请在代码中查阅以获取最新的默认值 *//*
//            httpProfile.setConnTimeout(60);
//            *//* SDK 会自动指定域名，通常无需指定域名，但访问金融区的服务时必须手动指定域名
//             * 例如 SMS 的上海金融区域名为 sms.ap-shanghai-fsi.tencentcloudapi.com *//*
//            httpProfile.setEndpoint("sms.tencentcloudapi.com");
//
//            *//* 非必要步骤:
//             * 实例化一个客户端配置对象，可以指定超时时间等配置 *//*
//            ClientProfile clientProfile = new ClientProfile();
//            *//* SDK 默认用 TC3-HMAC-SHA256 进行签名
//             * 非必要请不要修改该字段 *//*
//            clientProfile.setSignMethod("HmacSHA256");
//            clientProfile.setHttpProfile(httpProfile);
//            *//* 实例化 SMS 的 client 对象
//             * 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 *//*
//            SmsClient client = new SmsClient(cred, "",clientProfile);
//            *//* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
//             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
//             * 属性可能是基本类型，也可能引用了另一个数据结构
//             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 *//*
//            SendSmsRequest req = new SendSmsRequest();
//
//            *//* 填充请求参数，这里 request 对象的成员变量即对应接口的入参
//             * 您可以通过官网接口文档或跳转到 request 对象的定义处查看请求参数的定义
//             * 基本类型的设置:
//             * 帮助链接：
//             * 短信控制台：https://console.cloud.tencent.com/smsv2
//             * sms helper：https://cloud.tencent.com/document/product/382/3773 *//*
//
//            *//* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 *//*
//            String appid = "17621672325";
//            req.setSmsSdkAppid(appid);
//
//            *//* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 *//*
//            String sign = "签名内容";
//            req.setSign(sign);
//
//            *//* 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] *//*
//            String senderid = "xxx";
//            req.setSenderId(senderid);
//
//            *//* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 *//*
//            String session = "xxx";
//            req.setSessionContext(session);
//
//            *//* 短信码号扩展号: 默认未开通，如需开通请联系 [sms helper] *//*
//            String extendcode = "xxx";
//            req.setExtendCode(extendcode);
//
//            *//* 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID *//*
//            String templateID = "400000";
//            req.setTemplateID(templateID);
//
//            *//* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
//             * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*//*
//            String[] phoneNumbers = {"+8621212313123", "+8612345678902", "+8612345678903"};
//            req.setPhoneNumberSet(phoneNumbers);
//
//            *//* 模板参数: 若无模板参数，则设置为空*//*
//            String[] templateParams = {"5678"};
//            req.setTemplateParamSet(templateParams);
//
//            *//* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
//             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 *//*
//            SendSmsResponse res = client.SendSms(req);
//
//            // 输出 JSON 格式的字符串回包
//            System.out.println(SendSmsResponse.toJsonString(res));
//
//            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
//            System.out.println(res.getRequestId());
//
//        } catch (TencentCloudSDKException e) {
//            e.printStackTrace();
//        }
//
//




    }




}
