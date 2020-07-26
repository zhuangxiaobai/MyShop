package com.zc.shop.admin.controller;

import com.zc.shop.admin.service.ShopcartService;
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

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody Shopcart shopcart) {

        int count = shopcartService.addShopcart(shopcart);

        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

   @ApiOperation("获取当前用户的购物车列表")
    @RequestMapping(value = "/shopcartList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ShopcartVo>> shopcartList(HttpServletRequest request) {

       //获取当前用户id
       Users user = (Users) request.getAttribute("user");
       Integer userId = user.getId();

       List<ShopcartVo>  shopcartVoList=  shopcartService.shopcartList(userId);

        return CommonResult.success(shopcartVoList);
    }

/*
    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        int count = cartItemService.updateQuantity(id, memberService.getCurrentMember().getId(), quantity);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改购物车中商品的件数")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateAttr(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(memberService.getCurrentMember().getId(), ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult clear() {
        int count = cartItemService.clear(memberService.getCurrentMember().getId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
*/


}
