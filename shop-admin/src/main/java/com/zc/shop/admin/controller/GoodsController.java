package com.zc.shop.admin.controller;


import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "GoodsController", description = "商品信息")
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;


    @ApiOperation(value = "商品添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody @ApiParam(value="添加商品对象")GoodsCreateParam goodsCreateParam, HttpServletRequest request) {

       //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


       //创建商品信息
        int count = goodsService.create(goodsCreateParam,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "商品搜索功能，带条件查现货商城里的商品")
    @RequestMapping(value = "/goodslistIndex", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult goodslistIndex(@RequestBody GoodsSelectParam goodsSelectParam) {



        return CommonResult.success(goodsService.goodslistIndex(goodsSelectParam));


    }

    @ApiOperation(value = "卖家获取自己的所有商品，带条件")
    @RequestMapping(value = "/goodsSelllist", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult goodslistIndex(@RequestBody GoodsSellListParam goodsSelectParam) {



        return CommonResult.success(goodsService.goodsSelllist(goodsSelectParam));


    }
    @ApiOperation(value = "修改上下架")
    @RequestMapping(value = "/updateIsOnSale", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIsOnSale(@RequestBody GoodsUpdateOnSaleParam goodsUpdateOnSaleParam) {


       int count  = goodsService.updateIsOnSale(goodsUpdateOnSaleParam);

        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }



    }


    @ApiOperation(value = "卖家通过id获取商品信息")
    @RequestMapping(value = "/selectGoodsById", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectGoodsById(@RequestParam("goodsId")Integer goodsId) {


        return CommonResult.success(goodsService.selectGoodsById(goodsId));

    }


    @ApiOperation(value = "卖家修改商品信息")
    @RequestMapping(value = "/updateGoodsInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectGoodsById(@RequestBody GoodsUpdateParam goodsUpdateParam) {


        int count = goodsService.updateGoodsInfo(goodsUpdateParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }



    }









}
