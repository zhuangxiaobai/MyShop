package com.zc.shop.admin.controller;

import com.zc.shop.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

//    @Value("${jwt.tokenHeader}")
//    private String tokenHeader;
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//    @Autowired
//    private UmsAdminService adminService;
//    @Autowired
//    private UmsRoleService roleService;
//
//    @ApiOperation(value = "用户注册")
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
//        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
//        if (umsAdmin == null) {
//            CommonResult.failed();
//        }
//        return CommonResult.success(umsAdmin);
//    }
//
//    @ApiOperation(value = "登录以后返回token")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
//        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
//        if (token == null) {
//            return CommonResult.validateFailed("用户名或密码错误");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", token);
//        tokenMap.put("tokenHead", tokenHead);
//        return CommonResult.success(tokenMap);
//    }
//
//    @ApiOperation(value = "刷新token")
//    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult refreshToken(HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader);
//        String refreshToken = adminService.refreshToken(token);
//        if (refreshToken == null) {
//            return CommonResult.failed("token已经过期！");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", refreshToken);
//        tokenMap.put("tokenHead", tokenHead);
//        return CommonResult.success(tokenMap);
//    }


    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCaptcha() {












        return CommonResult.failed();
    }




}
