package com.zc.shop.admin.controller;



import com.zc.shop.admin.dto.StoreinfoCreateParam;
import com.zc.shop.admin.dto.PageParam;
import com.zc.shop.admin.dto.StoreinfoDetailParam;
import com.zc.shop.admin.dto.StoreinfoListParam;
import com.zc.shop.admin.service.StoreinfoService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "卖家通过当前userId获取店铺对象,一个用户对应一个店铺")
    @RequestMapping(value = "/storeinfoByUserId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult StoreinfoByUserId(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


       return CommonResult.success(storeinfoService.StoreinfoByUserId(userId));


    }

    @ApiOperation(value = "店铺搜索功能，模糊搜索店铺列表按创建时间排序")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult list(@RequestBody StoreinfoListParam storeinfoListParam) {



        return CommonResult.success(storeinfoService.StoreinfoList(storeinfoListParam));


    }



    @ApiOperation(value = "买家获取店铺详情")
    @RequestMapping(value = "/storeinfoDetails", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult storeinfoDetails(@RequestBody StoreinfoDetailParam storeinfoDetailParam) {


        return CommonResult.success(storeinfoService.StoreinfoDetails(storeinfoDetailParam));


    }


    @ApiOperation(value = "获取店铺详情里的品名，仓库，厂家")
    @RequestMapping(value = "/storeinfoDetailsSelectParam", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult storeinfoDetailsSelectParam(@RequestParam("storeinfoId") Short storeinfoId) {



        return CommonResult.success(storeinfoService.storeinfoDetailsSelectParam(storeinfoId));


    }





}
