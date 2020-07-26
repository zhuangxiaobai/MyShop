package com.zc.shop.admin.controller;



import com.zc.shop.admin.dto.StoreinfoCreateParam;
import com.zc.shop.admin.service.StoreinfoService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "StoreinfoController", description = "店铺")
@RequestMapping("/storeinfo")
public class StoreInfoController {

    @Autowired
    private StoreinfoService storeinfoService;

    @ApiOperation(value = "店铺添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @ApiParam(value="添加店铺对象")StoreinfoCreateParam storeinfoCreateParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建商品信息
        int count = storeinfoService.create(storeinfoCreateParam,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "通过当前userId获取店铺对象,一个用户对应一个店铺")
    @RequestMapping(value = "/StoreinfoByUserId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult StoreinfoByUserId(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


       return CommonResult.success(storeinfoService.StoreinfoByUserId(userId));


    }














}
