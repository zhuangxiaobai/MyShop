package com.zc.shop.admin.controller;

import com.zc.shop.admin.dto.OrderCreateParam;
import com.zc.shop.admin.service.MarginService;
import com.zc.shop.admin.service.OrderService;
import com.zc.shop.admin.vo.ShopcartGaiVo;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Margin;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = "MarginController", description = "保证金表")
@RequestMapping("/margin")
public class MarginController {


    @Autowired
    private MarginService marginService;


    @ApiOperation(value = "支付保证金")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody Margin margin, HttpServletRequest request) {


        //创建订单信息
        int count = marginService.create(margin);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation("查看付款凭证")
    @RequestMapping(value = "/marginByOrderCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Margin> selectMarginByOrderCode(@RequestParam("orderCode")String orderCode,HttpServletRequest request) {

        Margin margin=  marginService.selectMarginByOrderCode(orderCode);

        return CommonResult.success(margin);
    }



}
