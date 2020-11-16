package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.*;
import com.zc.shop.admin.service.EmployeesService;
import com.zc.shop.admin.vo.DepartmentVo;
import com.zc.shop.admin.vo.EmployeesVo;
import com.zc.shop.admin.vo.PagesVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private DepartmentExtMapper departmentExtMapper;

    @Autowired
    private PositionExtMapper positionExtMapper;


    @Autowired
    private RolesExtMapper rolesExtMapper;


    @Autowired
    private PagesExtMapper pagesExtMapper;

    @Autowired
    private EmployeesExtMapper employeesExtMapper;

    @Autowired
    private UsersExtMapper usersExtMapper;

    @Autowired
    private  CertificationExtMapper certificationExtMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createDepartmrnt(DepartmentInsertParam departmentInsertParam) {


        LocalDateTime now = LocalDateTime.now();


        Department department = new Department();
        BeanUtil.copyProperties(departmentInsertParam,department);

        department.setCreatedAt(now);
        department.setUpdatedAt(now);


        return departmentExtMapper.insertSelective(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDepartmrnt(DepartmentInsertParam departmentInsertParam) {

        LocalDateTime now = LocalDateTime.now();


        Department department = new Department();
        BeanUtil.copyProperties(departmentInsertParam,department);

        department.setUpdatedAt(now);


        return departmentExtMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map departmentList(Integer c_id) {


        List<DepartmentVo>  departmentVos= departmentExtMapper.selectMenuTree(c_id);


       Map map  = new HashMap();
       map.put("departmentVos",departmentVos);

       return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPosition(PositionInsertParam positionInsertParam) {

        LocalDateTime now = LocalDateTime.now();


        Position position = new Position();
        BeanUtil.copyProperties(positionInsertParam,position);

        position.setCreatedAt(now);
        position.setUpdatedAt(now);


        return positionExtMapper.insertSelective(position);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePosition(PositionInsertParam positionInsertParam) {

        LocalDateTime now = LocalDateTime.now();


        Position position = new Position();
        BeanUtil.copyProperties(positionInsertParam,position);


        position.setUpdatedAt(now);


        return positionExtMapper.updateByPrimaryKeySelective(position);
    }

    @Override
    public Map positionList(PositionSelectParam positionSelectParam) {


        //分页查询处理
        Integer startPage = positionSelectParam.getPageParam().getStartPage();
        Integer pageSize = positionSelectParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        positionSelectParam.getPageParam().setStartPage(start);



        List<Position>  positions= positionExtMapper.selectPositionList(positionSelectParam);
        int  total= positionExtMapper.selectPositionListNum(positionSelectParam);



        Map map  = new HashMap();
        map.put("positions",positions);
        map.put("total",total);
        return map;



    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePosition(Long id) {


        return positionExtMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRole(RoleInsertParam roleInsertParam) {

        LocalDateTime now = LocalDateTime.now();


        Roles roles = new Roles();
        BeanUtil.copyProperties(roleInsertParam,roles);

        roles.setCreatedAt(now);
        roles.setUpdatedAt(now);


        return rolesExtMapper.insertSelective(roles);



    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(RoleInsertParam roleInsertParam) {


        LocalDateTime now = LocalDateTime.now();


        Roles roles = new Roles();
        BeanUtil.copyProperties(roleInsertParam,roles);

        roles.setUpdatedAt(now);


        return rolesExtMapper.updateByPrimaryKeySelective(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRole(Long id) {

        return rolesExtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Map roleList(RoleSelectParam roleSelectParam) {


        //分页查询处理
        Integer startPage = roleSelectParam.getPageParam().getStartPage();
        Integer pageSize = roleSelectParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        roleSelectParam.getPageParam().setStartPage(start);



        List<Roles>  rolesList= rolesExtMapper.selectRolesList(roleSelectParam);
        int  total= rolesExtMapper.selectRolesListNum(roleSelectParam);



        Map map  = new HashMap();
        map.put("rolesList",rolesList);
        map.put("total",total);
        return map;

    }

    @Override
    public Map pagesList() {


        List<PagesVo>  pagesVos= pagesExtMapper.selectMenuTree();


        Map map  = new HashMap();
        map.put("pagesVos",pagesVos);

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int inviteEmployee(EmployeesInsertParam employeesInsertParam) {

        //看在user表里面存不存在，存在就能添加，不在就报错
        LocalDateTime now = LocalDateTime.now();

        String phoneNumber = employeesInsertParam.getPhoneNumber();

        Users usersExist = usersExtMapper.selectUserByUserName(phoneNumber);

        if(usersExist == null){
            throw new BusinessException(ResultCode.USERNAMENOTEXIST);
        }


        //添加员工表
        Employees employees = new Employees();
        BeanUtil.copyProperties(employeesInsertParam,employees);
        employees.setUserId(usersExist.getId());
        employees.setCreatedAt(now);
        employees.setUpdatedAt(now);

        int i    = employeesExtMapper.insertSelective(employees);


        //去修改user表中的role_id和c_id


        usersExist.setRoleId(employeesInsertParam.getRoleId());
        usersExist.setcId(employeesInsertParam.getCId());
        usersExist.setUpdatedAt(now);
        int k =  usersExtMapper.updateByPrimaryKeySelective(usersExist);


        return k;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEmployee(EmployeesInsertParam employeesInsertParam) {


        LocalDateTime now = LocalDateTime.now();

        String phoneNumber = employeesInsertParam.getPhoneNumber();

        Users usersExist = usersExtMapper.selectUserByUserName(phoneNumber);

        if(usersExist == null){
            throw new BusinessException(ResultCode.USERNAMENOTEXIST);
        }


        //添加员工表
        Employees employees = new Employees();
        BeanUtil.copyProperties(employeesInsertParam,employees);
        employees.setUserId(usersExist.getId());
        employees.setUpdatedAt(now);

        int i    = employeesExtMapper.updateByPrimaryKeySelective(employees);


        //去修改user表中的role_id和c_id


        usersExist.setRoleId(employeesInsertParam.getRoleId());
        usersExist.setcId(employeesInsertParam.getCId());
        usersExist.setUpdatedAt(now);
        int k =  usersExtMapper.updateByPrimaryKeySelective(usersExist);


        return k;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmployee(Long id) {

        LocalDateTime now = LocalDateTime.now();


        Employees employees = employeesExtMapper.selectByPrimaryKey(id);

        //不存在
        if(employees == null){
             throw  new BusinessException("此条员工信息不存在");
        }

        //存在
        int i   = employeesExtMapper.deleteByPrimaryKey(id);


        //去修改user表中的role_id和c_id

        Users users = new Users();
        users.setId(employees.getUserId());
        users.setRoleId(0L);
        users.setcId(0);
        users.setUpdatedAt(now);
        int k =  usersExtMapper.updateByPrimaryKeySelective(users);


        return k;
    }

    @Override
    public Map employeeList(EmployeesSelectParam employeesSelectParam) {


        //分页查询处理
        Integer startPage = employeesSelectParam.getPageParam().getStartPage();
        Integer pageSize = employeesSelectParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        employeesSelectParam.getPageParam().setStartPage(start);



        List<EmployeesVo>  employeesVoList= employeesExtMapper.selectEmployeesVoList(employeesSelectParam);
        int  total= employeesExtMapper.selectEmployeesVoListNum(employeesSelectParam);



        Map map  = new HashMap();
        map.put("employeesVoList",employeesVoList);
        map.put("total",total);
        return map;

    }

    @Override
    public Map myPagesList(Integer userId) {


        Users users = usersExtMapper.selectByPrimaryKey(userId);
        Long roleId = users.getRoleId();
        Integer cId = users.getcId();

        Certification certification = certificationExtMapper.selectByUserId(userId);


        List<Pages> pagesList = new Page();
        Map  map  = new HashMap();
        //公司所有者，全可以访问
        if(certification !=null){
           //TODO

            map.put("pagesList","公司所有者都能访问");
            return map;

        }

        //注册完没被邀请两个都是0
        if(roleId == 0 && cId == 0){

          //TODO


            map.put("pagesList","初始化注册用户最低页面权限");
            return map;

        }

        //剩下的就是被邀请的了


        Roles roles = rolesExtMapper.selectByPrimaryKey(roleId);

        String permission = roles.getPermission();

        String[] split = permission.split(",");


        for(String pageId:split){

            Pages page = pagesExtMapper.selectByPrimaryKey(Long.parseLong(pageId));
            pagesList.add(page);

        }


       map.put("pagesList",pagesList);


        return map;
    }
}
