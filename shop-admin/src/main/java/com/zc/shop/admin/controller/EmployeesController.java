package com.zc.shop.admin.controller;


import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.service.EmployeesService;
import com.zc.shop.common.api.CommonResult;
import com.zc.shop.mbg.po.Certification;
import com.zc.shop.mbg.po.Position;
import com.zc.shop.mbg.po.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "EmployeesController", description = "员工设置相关")
@RequestMapping("/employees")
public class EmployeesController {


    @Autowired
    private EmployeesService employeesService;



    @ApiOperation(value = "添加部门")
    @RequestMapping(value = "/insertDepartment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertDepartment(@RequestBody @ApiParam(value="添加部门对象")DepartmentInsertParam departmentInsertParam, HttpServletRequest request) {

        //获取当前用户id
//        Users user = (Users) request.getAttribute("user");
//        Integer userId = user.getId();


        //创建认证信息
        int count = employeesService.createDepartmrnt(departmentInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "修改部门")
    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDepartment(@RequestBody @ApiParam(value="修改部门对象")DepartmentInsertParam departmentInsertParam, HttpServletRequest request) {

        //获取当前用户id
//        Users user = (Users) request.getAttribute("user");
//        Integer userId = user.getId();


        //创建认证信息
        int count = employeesService.updateDepartmrnt(departmentInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "获取企业部门(树形结构)")
    @RequestMapping(value = "/departmentList/{c_id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult departmentList(@PathVariable("c_id") Integer c_id, HttpServletRequest request) {


        return CommonResult.success(employeesService.departmentList(c_id));


    }





    @ApiOperation(value = "添加岗位")
    @RequestMapping(value = "/insertPosition", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertPosition(@RequestBody @ApiParam(value="添加岗位对象")PositionInsertParam positionInsertParam, HttpServletRequest request) {


        //创建认证信息
        int count = employeesService.insertPosition(positionInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "修改岗位")
    @RequestMapping(value = "/updatePosition", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePosition(@RequestBody @ApiParam(value="修改岗位对象")PositionInsertParam positionInsertParam, HttpServletRequest request) {

        //创建认证信息
        int count = employeesService.updatePosition(positionInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "查询岗位集合")
    @RequestMapping(value = "/positionList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult positionList(@RequestBody PositionSelectParam positionSelectParam, HttpServletRequest request) {


        return CommonResult.success(employeesService.positionList(positionSelectParam));


    }


    @ApiOperation(value = "删除岗位")
    @RequestMapping(value = "/deletePosition/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deletePosition(@PathVariable("id") Long id, HttpServletRequest request) {

        //创建认证信息
        int count = employeesService.deletePosition(id);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }



    @ApiOperation(value = "添加角色")
    @RequestMapping(value = "/insertRole", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertRole(@RequestBody @ApiParam(value="添加角色对象")RoleInsertParam roleInsertParam , HttpServletRequest request) {


        //创建认证信息
        int count = employeesService.insertRole(roleInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestBody @ApiParam(value="修改岗位对象")RoleInsertParam roleInsertParam, HttpServletRequest request) {

        //创建认证信息
        int count = employeesService.updateRole(roleInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "查询角色集合")
    @RequestMapping(value = "/roleList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult roleList(@RequestBody RoleSelectParam roleSelectParam, HttpServletRequest request) {


        return CommonResult.success(employeesService.roleList(roleSelectParam));


    }


    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteRole(@PathVariable("id") Long id, HttpServletRequest request) {

        //创建认证信息
        int count = employeesService.deleteRole(id);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }



    @ApiOperation(value = "获取菜单信息(树形结构)（设置角色下面的菜单分配）")
    @RequestMapping(value = "/pagesList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult pagesList(HttpServletRequest request) {


        return CommonResult.success(employeesService.pagesList());


    }

    @ApiOperation(value = "用户登录成功后需要获取自身的可见菜单信息(树形结构)")
    @RequestMapping(value = "/myPagesList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult myPagesList(HttpServletRequest request) {

        //获取当前用户id
        Users user = (Users) request.getAttribute("user");
        Integer userId = user.getId();



        return CommonResult.success(employeesService.myPagesList(userId));


    }



    @ApiOperation(value = "邀请员工(必须是已经注册过的用户)")
    @RequestMapping(value = "/inviteEmployee", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult inviteEmployee(@RequestBody @ApiParam(value="添加员工对象")EmployeesInsertParam employeesInsertParam , HttpServletRequest request) {


        //创建认证信息
        int count = employeesService.inviteEmployee(employeesInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "修改员工信息")
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateEmployee(@RequestBody @ApiParam(value="修改员工信息")EmployeesInsertParam employeesInsertParam, HttpServletRequest request) {

        //创建认证信息
        int count = employeesService.updateEmployee(employeesInsertParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "查询员工集合")
    @RequestMapping(value = "/employeeList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult employeeList(@RequestBody EmployeesSelectParam employeesSelectParam, HttpServletRequest request) {


        return CommonResult.success(employeesService.employeeList(employeesSelectParam));


    }


    @ApiOperation(value = "删除员工")
    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteEmployee(@PathVariable("id") Long id, HttpServletRequest request) {

        //创建认证信息
        int count = employeesService.deleteEmployee(id);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }







}
