package com.zc.shop.admin.controller;


import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.InventoryService;
import com.zc.shop.admin.service.InvoiceService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Inventory;
import com.zc.shop.mbg.po.Invoice;
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
@Api(tags = "InventoryController", description = "库存操作")
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @ApiOperation(value = "入库操作")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @ApiParam(value="添加库存集合对象")List<Inventory> inventoryList, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建商品信息
        int count = inventoryService.createInventory(inventoryList,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "出库操作")
    @RequestMapping(value = "/chuku", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult chuku(@RequestBody @ApiParam(value="添加库存集合出库对象")List<ChuKuParam> chuKuParams, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建商品信息
        int count = inventoryService.createChuku(chuKuParams,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }
    @ApiOperation(value = "库存概况查询，资源列表下库存资源选择查询")
    @RequestMapping(value = "/inventoryList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult inventoryList(@RequestBody InventorySelectParam inventorySelectParam,HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(inventoryService.inventoryList(inventorySelectParam,userId));


    }

    @ApiOperation(value = "入库记录查询")
    @RequestMapping(value = "/ruKuList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ruKuList(@RequestBody RuKuChuKuParam ruKuChuKuParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(inventoryService.ruKuList(ruKuChuKuParam,userId));


    }

    @ApiOperation(value = "出库记录查询")
    @RequestMapping(value = "/chuKuList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult chuKuList(@RequestBody RuKuChuKuParam ruKuChuKuParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(inventoryService.chuKuList(ruKuChuKuParam,userId));


    }


    @ApiOperation(value = "入库选择采购单")
    @RequestMapping(value = "/ruKuCaiGouList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ruKuCaiGouList(@RequestBody RuKuCaiGouParam ruKuCaiGouParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(inventoryService.ruKuCaiGouList(ruKuCaiGouParam,userId));


    }

    @ApiOperation(value = "出库选择销售单")
    @RequestMapping(value = "/chuKuXiaoShouList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult chuKuXiaoShouList(@RequestBody ChuKuXiaoShouParam chuKuXiaoShouParam, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(inventoryService.chuKuXiaoShouList(chuKuXiaoShouParam,userId));


    }

    @ApiOperation(value = "资源列表下库存发布添加到商品表")
    @RequestMapping(value = "/inventoryAddGoods", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult InventoryAddGoods(@RequestBody List<GoodsCreateParam> goodsCreateParams,HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();

        return CommonResult.success(inventoryService.inventoryAddGoods(goodsCreateParams,userId));


    }








}
