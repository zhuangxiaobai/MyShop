package com.zc.shop.admin.controller;


import com.zc.shop.admin.service.InvoiceService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Invoice;
import com.zc.shop.mbg.po.Makeout;
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
@Api(tags = "InvoiceController", description = "发票，开票信息")
@RequestMapping("/invoice")
public class InvoiceController {



    @Autowired
    private InvoiceService invoiceService;


    @ApiOperation(value = "发票添加，发票地址中的发票信息管理")
    @RequestMapping(value = "/createInvoice", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createInvoice(@RequestBody @ApiParam(value="添加发票对象")Invoice invoice, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建商品信息
        int count = invoiceService.createInvoice(invoice,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "开票添加，发票地址中的开票信息管理")
    @RequestMapping(value = "/createMakeout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createMakeout(@RequestBody @ApiParam(value="添加开票对象") Makeout makeout, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建商品信息
        int count = invoiceService.createMakeOut(makeout,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "获取自己的所有发票信息管理")
    @RequestMapping(value = "/invoiceList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult invoiceList(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(invoiceService.invoiceList(userId));


    }

    @ApiOperation(value = "获取自己的所有开票信息管理")
    @RequestMapping(value = "/makeoutList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult makeoutList(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(invoiceService.makeoutList(userId));


    }

    @ApiOperation(value = "修改发票信息管理记录")
    @RequestMapping(value = "/updateInvoiceById", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInvoiceById( @RequestBody Invoice invoice) {


        int count  = invoiceService.updateInvoiceById(invoice);

        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }



    }

    @ApiOperation(value = "修改开票信息管理记录")
    @RequestMapping(value = "/updateMakeoutById", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateMakeoutById(@RequestBody Makeout makeout) {


        int count  = invoiceService.updateMakeoutById(makeout);

        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }



    }








}
