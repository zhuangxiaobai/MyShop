package com.zc.shop.admin.controller;


import com.zc.shop.admin.dto.MessageListSelectParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.service.MessageService;
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
@Api(tags = "MessageController", description = "通知表")
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private MessageService messageService;



    @ApiOperation(value = "获取我的消息(全部,系统，私信，代办)")
    @RequestMapping(value = "/myMessageList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult myMessageList(@RequestBody @ApiParam(value="查询用户通知对象") MessageListSelectParam messageListSelectParam, HttpServletRequest request) {



        return CommonResult.success(messageService.myMessageList(messageListSelectParam));


    }






}
