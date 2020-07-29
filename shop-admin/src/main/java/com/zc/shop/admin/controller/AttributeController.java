package com.zc.shop.admin.controller;

import com.zc.shop.admin.service.AttributeService;
import com.zc.shop.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "AttributeController", description = "品种,品名表")
@RequestMapping("/attribute")
public class AttributeController {

       @Autowired
       private AttributeService attributeService;

    @ApiOperation(value = "获取所有品种")
    @RequestMapping(value = "/allPingZhong", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult allPingZhong() {

        return CommonResult.success(attributeService.allPingZhong());

    }

    @ApiOperation(value = "通过品种获取品名对象")
    @RequestMapping(value = "/ListByPingZhong", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ListByPingZhong(@RequestParam("pingZhong") String pingZhong) {


        return CommonResult.success(attributeService.ListByPingZhong(pingZhong));

    }

    @ApiOperation(value = "首页全部商品分类")
    @RequestMapping(value = "/AllAttributeVo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult AllAttributeVo() {


        return CommonResult.success(attributeService.AllAttributeVo());

    }


}
