package com.zc.shop.admin.controller;

import com.zc.shop.admin.service.WareService;
import com.zc.shop.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "WareController", description = "仓库")
@RequestMapping("/ware")
public class WareController {

    @Autowired
    private WareService wareService;

    @ApiOperation(value = "获取所有仓库对象")
    @RequestMapping(value = "/allWare", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult allWare() {

        return CommonResult.success(wareService.allWare());

    }





}
