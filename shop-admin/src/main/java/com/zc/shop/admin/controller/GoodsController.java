package com.zc.shop.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.GoodsCreateParam;
import com.zc.shop.admin.dto.UsersParam;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.admin.util.MyCacheUtil;
import com.zc.shop.admin.vo.UsersVo;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.common.api.ResultCode;
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








}
