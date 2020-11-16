package com.zc.shop.admin.controller;

import com.zc.shop.admin.service.ShopcartService;
import com.zc.shop.admin.vo.ShopcartGaiVo;
import com.zc.shop.admin.vo.ShopcartVo;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Shopcart;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = "ShopcartController", description = "购物车")
@RequestMapping("/shopcart")
public class ShopcartController {

    @Autowired
    private ShopcartService shopcartService;

    @ApiOperation("添加商品到购物车，需要传入userId")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody Shopcart shopcart,HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        shopcart.setUserId(userId);
        int count = shopcartService.addShopcart(shopcart);

        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

   @ApiOperation("获取当前用户的购物车列表")
    @RequestMapping(value = "/shopcartList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ShopcartGaiVo>> shopcartList(HttpServletRequest request) {

       //获取当前用户id
       Users user = (Users) request.getAttribute("user");
       Integer userId = user.getId();

       List<ShopcartGaiVo> shopcartGaiVoList=  shopcartService.shopcartList(userId);

        return CommonResult.success(shopcartGaiVoList);
    }


    @ApiOperation("修改购物车中某个商品的数量，需要传入userId")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody Shopcart shopcart) {
        int count = shopcartService.updateShopcart(shopcart);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("删除选中商品，清空下架商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("shopcartIds") List<Short> shopcartIds) {



        int count = shopcartService.deleteShopcart(shopcartIds);;
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }






}
