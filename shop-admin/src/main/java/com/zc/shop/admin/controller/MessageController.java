package com.zc.shop.admin.controller;


import com.zc.shop.admin.dto.MessageListSelectParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.dto.UpdatePersonalParam;
import com.zc.shop.admin.service.MessageService;
import com.zc.shop.common.api.CommonResult;
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
    //修改(批量已读)
    @ApiOperation(value = "修改已读(单个和批量),传参为 [1,2,3...]")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@RequestBody @ApiParam(value="修改已读id的集合")List<Integer> readingHaved, HttpServletRequest request) {


        //传入去修改
        int count = messageService.updateStatus(readingHaved);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }





    //删除(批量删除)
    @ApiOperation(value = "删除通知(单个和批量),传参为 [1,2,3...]")
    @RequestMapping(value = "/updateDelete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDelete(@RequestBody @ApiParam(value="删除已读id的集合")List<Integer> needDelete, HttpServletRequest request) {


        //传入去修改
        int count = messageService.updateDelete(needDelete);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }










}
