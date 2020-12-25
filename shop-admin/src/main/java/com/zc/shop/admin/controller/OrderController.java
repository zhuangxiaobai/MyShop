package com.zc.shop.admin.controller;

import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.OrderService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Shopcart;
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
@Api(tags = "OrderController", description = "订单表")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


  /*  @ApiOperation(value = "用户下订单,传购物车对象集合的json字符串")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody @ApiParam(value="购物车对象集合")OrderCreateParam orderCreateParam, HttpServletRequest request) {


        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        //创建订单信息
        int count = orderService.create(orderCreateParam,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }*/
    @ApiOperation(value = "用户创建订单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody @ApiParam(value="购物车对象集合")List<SupplierGoodsParam> supplierGoodsParamList, HttpServletRequest request) {


        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        //创建订单信息
        int count = orderService.createOrder(supplierGoodsParamList,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }



    @ApiOperation(value = "卖家获取我的订单")
    @RequestMapping(value = "/mySellOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult mySellOrder(@RequestBody @ApiParam(value="查询订单对象") OrderSellSelectParam orderSellSelectParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(orderService.mySellOrder(orderSellSelectParam,userId));


    }

    @ApiOperation(value = "买家获取我的订单")
    @RequestMapping(value = "/myBuyOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myBuyOrder(@RequestBody @ApiParam(value="查询订单对象") OrderBuySelectParam orderBuySelectParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(orderService.myBuyOrder(orderBuySelectParam,userId));

    }


    @ApiOperation("修改订单状态接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody OrderParam orderParam,HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();



        int count = orderService.update(orderParam,userId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation(value = "后台首页我的订单各种状态(包括买的和卖的订单)(返回订单集合)")
    @RequestMapping(value = "/myOrderIndex", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult myOrderIndex(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        return CommonResult.success(orderService.myOrderIndex(userId));

    }



//    @ApiOperation(value = "卖家订单中的查看详情")
//    @RequestMapping(value = "/mySellOrder", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult mySellOrder(@RequestBody @ApiParam(value="查询订单对象") OrderSellSelectParam orderSellSelectParam, HttpServletRequest request) {
//
//        //获取当前用户id
//        Users user = (Users) request.getAttribute("user");
//        Integer userId = user.getId();
//
//
//        return CommonResult.success(orderService.mySellOrder(orderSellSelectParam,userId));
//
//    }
//
//    @ApiOperation(value = "买家订单中的查看详情")
//    @RequestMapping(value = "/mySellOrder", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult mySellOrder(@RequestBody @ApiParam(value="查询订单对象") OrderSellSelectParam orderSellSelectParam, HttpServletRequest request) {
//
//        //获取当前用户id
//        Users user = (Users) request.getAttribute("user");
//        Integer userId = user.getId();
//
//
//        return CommonResult.success(orderService.mySellOrder(orderSellSelectParam,userId));
//
//    }



}
