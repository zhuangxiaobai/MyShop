//package com.zc.shop.admin.controller;
//
//import com.zc.shop.admin.dto.StoreinfoUpdateParam;
//import com.zc.shop.admin.dto.WareCreateParam;
//import com.zc.shop.admin.dto.WareUpdateParam;
//import com.zc.shop.admin.service.WareService;
//import com.zc.shop.common.api.CommonResult;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@Api(tags = "WareController", description = "仓库")
//@RequestMapping("/ware")
//public class WareController {
//
//    @Autowired
//    private WareService wareService;
//
//    @ApiOperation(value = "获取所有仓库对象")
//    @RequestMapping(value = "/allWare", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult allWare() {
//
//        return CommonResult.success(wareService.allWare());
//
//    }
//
//
//    @ApiOperation(value = "通过id获得仓库")
//    @RequestMapping(value = "/getWareById", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult getWareById(@RequestParam Short wareId) {
//
//        return CommonResult.success(wareService.getWareById(wareId));
//
//    }
//
//
//    @ApiOperation("创建仓库")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@RequestBody WareCreateParam wareCreateParam) {
//        int count = wareService.createWare(wareCreateParam);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//
//    @ApiOperation("修改仓库信息")
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@RequestBody WareUpdateParam wareUpdateParam) {
//        int count = wareService.updateWare(wareUpdateParam);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//
//
//}
