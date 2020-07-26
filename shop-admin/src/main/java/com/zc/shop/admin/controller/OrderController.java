package com.zc.shop.admin.controller;

import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.service.OrderService;
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
@Api(tags = "OrderController", description = "订单表")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "卖家获取我的订单")
    @RequestMapping(value = "/mySellOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult mySellOrder(@RequestBody @ApiParam(value="查询订单对象") OrderSellSelectParam orderSellSelectParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(orderService.mySellOrder(orderSellSelectParam,userId));

    }

  /*  @ApiOperation(value = "买家获取我的订单")
    @RequestMapping(value = "/myBuyOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myBuyOrder(@RequestBody @ApiParam(value="查询订单对象") OrderSellSelectParam orderSellSelectParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(orderService.myBuyOrder(orderSellSelectParam,userId));

    }
*/









}
