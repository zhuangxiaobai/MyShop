package com.zc.shop.admin.controller;


import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.RegiinvoiceService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Regiinvoice;
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

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = "RegiinvoiceController", description = "开发票管理下的")
@RequestMapping("/regiinvoice")
public class RegiinvoiceController {

    @Autowired
    private RegiinvoiceService regiinvoiceService;


    @ApiOperation(value = "卖家进行发票登记")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody @ApiParam(value="发票登记对象")ReginivoiceCreateParam reginivoiceCreateParam, HttpServletRequest request) {


        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        //创建发票登记信息
        int count = regiinvoiceService.createRegiinvoice(reginivoiceCreateParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }



    @ApiOperation(value = "卖家获取我的进项发票，自己开的")
    @RequestMapping(value = "/myInRegiinvoice", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myInRegiinvoice(@RequestBody @ApiParam(value="进项发票查询对象") RegiinvoiceInParam regiinvoiceInParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(regiinvoiceService.myInRegiinvoice(regiinvoiceInParam,userId));

    }

    @ApiOperation(value = "买家获取我的销项发票，自己得的")
    @RequestMapping(value = "/myOutRegiinvoice", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myOutRegiinvoice(@RequestBody @ApiParam(value="销项发票查询对象") RegiinvoiceOutParam regiinvoiceOutParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(regiinvoiceService.myOutRegiinvoice(regiinvoiceOutParam,userId));

    }


    @ApiOperation("修改发票开具状态接口")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@RequestBody RegiinvoiceStatusParam regiinvoiceStatusParam) {
        int count = regiinvoiceService.updateStatus(regiinvoiceStatusParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }




    @ApiOperation(value = "卖家我的进项发票中的查看")
    @RequestMapping(value = "/myInRegiChaKan", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myInRegiChaKan(@RequestBody @ApiParam(value="进项发票中的查看查询对象")RegiinvoiceInChaKanParam regiinvoiceInChaKanParam , HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(regiinvoiceService.myInRegiinvoiceChaKan(regiinvoiceInChaKanParam,userId));

    }

    @ApiOperation(value = "买家我的销项发票中的查看")
    @RequestMapping(value = "/myOutRegiChaKan", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myOutRegiChaKan(@RequestBody @ApiParam(value="销项发票中的查看查询对象")RegiinvoiceOutChaKanParam regiinvoiceOutChaKanParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(regiinvoiceService.myOutRegiinvoiceChanKan(regiinvoiceOutChaKanParam,userId));

    }




    @ApiOperation(value = "发票查询(卖家)")
    @RequestMapping(value = "/regiinvoiceSearch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult regiinvoiceSearch(@RequestBody @ApiParam(value="发票查询对象")RegiinvoiceSearchParam regiinvoiceSearchParam , HttpServletRequest request) {


        return CommonResult.success(regiinvoiceService.regiinvoiceSearch(regiinvoiceSearchParam));

    }










}
