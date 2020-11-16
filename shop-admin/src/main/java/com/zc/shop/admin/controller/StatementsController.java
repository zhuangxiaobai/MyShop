package com.zc.shop.admin.controller;


import com.zc.shop.admin.service.StatementsService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Lading;
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
@Api(tags = "StatementsController", description = "结算表")
@RequestMapping("/statements")
public class StatementsController {


    @Autowired
    private StatementsService statementsService;



   /* @ApiOperation(value = "卖家结算单添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @ApiParam(value="添加提单对象") List<Lading> ladingList, HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();


        //创建提单信息
        int count = statementsService.createStatements(ladingList,userId);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }
*/









}
