package com.zc.shop.admin.controller;

import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.LadingService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Lading;
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
import java.util.List;

@Controller
@Api(tags = "LadingController", description = "提单表")
@RequestMapping("/lading")
public class LadingController {

    @Autowired
    private LadingService ladingService;

  /*  @ApiOperation(value = "提货申请,提单添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @ApiParam(value="添加提单对象") LadingCreateParam ladingCreateParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建提单信息
        int count = ladingService.create(ladingCreateParam,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }*/

    @ApiOperation(value = "买家提货申请,提单添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @ApiParam(value="添加提单对象") List<Lading> ladingList, HttpServletRequest request) {



        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建提单信息
        int count = ladingService.createLadings(ladingList,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation("修改提单状态的")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody LadingParam ladingParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();



        int count = ladingService.updateLadingStatus(ladingParam,userId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }



    @ApiOperation(value = "卖家获取我的放货单")
    @RequestMapping(value = "/mySellLading", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult mySellOrder(@RequestBody @ApiParam(value="查询放货单对象") LadingSellSelectParam ladingSellSelectParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(ladingService.mySellLading(ladingSellSelectParam,userId));

    }

    @ApiOperation(value = "买家获取我的提货单")
    @RequestMapping(value = "/myBuyLading", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myBuyOrder(@RequestBody @ApiParam(value="查询提单对象") LadingBuySelectParam ladingBuySelectParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(ladingService.myBuyLading(ladingBuySelectParam,userId));

    }



    @ApiOperation("录入实提")
    @RequestMapping(value = "/updateShiTi", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateShiTi(@RequestBody List<ShiTiParam> shiTiParam) {
        int count = ladingService.shiTiParam(shiTiParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }



    @ApiOperation(value = "卖家查看实提出库页面")
    @RequestMapping(value = "/shitichuku", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult shitichuku(@RequestBody ShiTiChuKuParam shiTiChuKuParam, HttpServletRequest request) {

        //获取当前用户id
//        Users user = (Users) request.getAttribute("user");
//        Integer userId = user.getId();


        return CommonResult.success(ladingService.selectShitichuku(shiTiChuKuParam));

    }




}
