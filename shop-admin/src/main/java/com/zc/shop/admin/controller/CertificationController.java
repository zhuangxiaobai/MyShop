package com.zc.shop.admin.controller;

import com.zc.shop.admin.service.CertificationService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Certification;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = "CertificationController", description = "认证接口")
@RequestMapping("/certification")
public class CertificationController {

    //注释111aa
    @Autowired
    private CertificationService certificationService;


    @ApiOperation(value = "添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insert(@RequestBody @ApiParam(value="添加认证对象") Certification certification, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建认证信息
        int count = certificationService.create(certification,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }




    @ApiOperation(value = "获取自身的认证信息")
    @RequestMapping(value = "/myCertification", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult myCertification(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(certificationService.myCertification(userId));


    }

    @ApiOperation(value = "获取指定用户id的公司认证信息")
    @RequestMapping(value = "/certificationByUserId", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult certificationByUserId(@RequestParam("userId") Integer userId, HttpServletRequest request) {



        return CommonResult.success(certificationService.selectCertificationByUserId(userId));


    }



}
