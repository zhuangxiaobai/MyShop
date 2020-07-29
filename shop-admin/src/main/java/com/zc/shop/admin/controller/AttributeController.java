package com.zc.shop.admin.controller;

import com.zc.shop.admin.dto.GoodsSelectAttrChangeParam;
import com.zc.shop.admin.service.AttributeService;
import com.zc.shop.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "AttributeController", description = "品种,品名表")
@RequestMapping("/attribute")
public class AttributeController {

       @Autowired
       private AttributeService attributeService;

    @ApiOperation(value = "获取所有品种")
    @RequestMapping(value = "/allPingZhong", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult allPingZhong() {

        return CommonResult.success(attributeService.allPingZhong());

    }

    @ApiOperation(value = "通过品种获取品名对象")
    @RequestMapping(value = "/listByPingZhong", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult listByPingZhong(@RequestParam("pingZhong") String pingZhong) {


        return CommonResult.success(attributeService.ListByPingZhong(pingZhong));

    }

    @ApiOperation(value = "首页全部商品分类列表")
    @RequestMapping(value = "/allAttributeVo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult allAttributeVo() {


        return CommonResult.success(attributeService.AllAttributeVo());

    }

    @ApiOperation(value = "点击现货商城跳转到的页面中的查询条件，品种，品名，规格，交货地，厂家")
    @RequestMapping(value = "/getGoodsSelect", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getGoodsSelect() {


        return CommonResult.success(attributeService.GetGoodsSelect());

    }

    @ApiOperation(value = "点击现货商城页面中的查询条件，点击品种，品名，  品名，材质,规格发生变化")
    @RequestMapping(value = "/getGoodsSelectAttributeChange", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getGoodsSelectAttributeChange(@RequestBody GoodsSelectAttrChangeParam goodsSelectAttrChangeParam) {


        return CommonResult.success(attributeService.getGoodsSelectAttributeChange(goodsSelectAttrChangeParam));

    }

}
