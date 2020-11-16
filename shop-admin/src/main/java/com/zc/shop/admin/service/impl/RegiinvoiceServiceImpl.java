package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.CertificationExtMapper;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.mapper.RegiinvoiceExtMapper;
import com.zc.shop.admin.mapper.RegiinvoicedetailExtMapper;
import com.zc.shop.admin.service.RegiinvoiceService;
import com.zc.shop.admin.vo.OrdersGaiVo;
import com.zc.shop.admin.vo.RegiinvoiceDetailChaKanVo;
import com.zc.shop.admin.vo.RegiinvoiceSearchVo;
import com.zc.shop.admin.vo.RegiinvoiceSimpleVo;
import com.zc.shop.mbg.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegiinvoiceServiceImpl implements RegiinvoiceService {


    @Autowired
    private OrderExtMapper orderExtMapper;

    @Autowired
    private RegiinvoiceExtMapper regiinvoiceExtMapper;

    @Autowired
    private RegiinvoicedetailExtMapper regiinvoicedetailExtMapper;

    @Autowired
    private CertificationExtMapper certificationExtMapper;




    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createRegiinvoice(ReginivoiceCreateParam reginivoiceCreateParam) {

        LocalDateTime now = LocalDateTime.now();

        //传入的总表
        Regiinvoice regiinvoice = reginivoiceCreateParam.getRegiinvoice();
        //已开未邮寄
        regiinvoice.setStatus(1);
        regiinvoice.setDate(now);
        //插入发票总表
        regiinvoiceExtMapper.insertSelective(regiinvoice);



        //传入的详情表数据
        List<Regiinvoicedetail> regiinvoicedetailList = reginivoiceCreateParam.getRegiinvoicedetailList();

        //循环插入发票详情表
        for(Regiinvoicedetail regiinvoicedetail:regiinvoicedetailList){

            regiinvoicedetail.setOrderCode(regiinvoice.getOrderCode());
            regiinvoicedetailExtMapper.insertSelective(regiinvoicedetail);

        }



        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(RegiinvoiceStatusParam regiinvoiceStatusParam) {
        LocalDateTime now = LocalDateTime.now();

        Regiinvoice regiinvoice = regiinvoiceExtMapper.selectByPrimaryKey(regiinvoiceStatusParam.getId());
        regiinvoice.setDate(now);
        regiinvoice.setStatus(regiinvoiceStatusParam.getStatus());
        int i = regiinvoiceExtMapper.updateByPrimaryKeySelective(regiinvoice);


        //int j = regiinvoiceExtMapper.updateByPrimaryKeySelective(regiinvoice);

        //4为买家已收票
        if(i>0 && regiinvoiceStatusParam.getStatus()== 4){
          //把此订单编号的所有订单表中的记录状态改为已完成,订单表中已完成的状态为7
            String orderCode = regiinvoice.getOrderCode();

            List<Order> orders  = orderExtMapper.selectOrderByOrderCode(orderCode);

            for(Order order1: orders) {
               //TODO
               //修改订单表状具体状态
                order1.setUpdatedAt(now);
                order1.setStatus(7);

                int k = orderExtMapper.updateByPrimaryKeySelective(order1);




            }


        }



        return i;
    }

    @Override
    public Map myInRegiinvoice(RegiinvoiceInParam regiinvoiceInParam, Integer userId) {



        //分页查询处理
        Integer startPage = regiinvoiceInParam.getStartPage();
        Integer pageSize = regiinvoiceInParam.getPageSize();
        Integer  start = (startPage-1)*pageSize;
        regiinvoiceInParam.setStartPage(start);


        Certification certification = certificationExtMapper.selectByUserId(userId);

        regiinvoiceInParam.setKaiCompanyName(certification.getCompany());

        //卖家的进项发票
        List<Regiinvoice> regiinvoices = regiinvoiceExtMapper.selectRegiinvoiceIn(regiinvoiceInParam);
        int total = regiinvoiceExtMapper.selectRegiinvoiceInTotal(regiinvoiceInParam);





        Map map  = new HashMap();
        map.put("regiinvoices",regiinvoices);
        map.put("total",total);

        return map;
    }

    @Override
    public Map myOutRegiinvoice(RegiinvoiceOutParam regiinvoiceOutParam, Integer userId) {


        //分页查询处理
        Integer startPage = regiinvoiceOutParam.getStartPage();
        Integer pageSize = regiinvoiceOutParam.getPageSize();
        Integer  start = (startPage-1)*pageSize;
        regiinvoiceOutParam.setStartPage(start);


        Certification certification = certificationExtMapper.selectByUserId(userId);

        regiinvoiceOutParam.setCompanyName(certification.getCompany());

        //卖家的进项发票
        List<Regiinvoice> regiinvoices = regiinvoiceExtMapper.selectRegiinvoiceOut(regiinvoiceOutParam);
        int total = regiinvoiceExtMapper.selectRegiinvoiceOutTotal(regiinvoiceOutParam);




        Map map  = new HashMap();
        map.put("regiinvoices",regiinvoices);
        map.put("total",total);

        return map;


    }

    @Override
    public Map myInRegiinvoiceChaKan(RegiinvoiceInChaKanParam regiinvoiceInChaKanParam, Integer userId) {

        //订单编号
        //String orderCode = regiinvoiceInChaKanParam.getOrderCode();


        List<RegiinvoiceDetailChaKanVo> regiinvoiceDetailChaKanVoList
                = regiinvoicedetailExtMapper.selectRegiinvoiceInDetailChaKan(regiinvoiceInChaKanParam);




        Map map  = new HashMap();
        map.put("regiinvoiceDetailChaKanVoList",regiinvoiceDetailChaKanVoList);

        return map;
    }

    @Override
    public Map myOutRegiinvoiceChanKan(RegiinvoiceOutChaKanParam regiinvoiceOutChaKanParam, Integer userId) {



        List<RegiinvoiceDetailChaKanVo> regiinvoiceDetailChaKanVoList
                = regiinvoicedetailExtMapper.selectRegiinvoiceOutDetailChaKan(regiinvoiceOutChaKanParam);





        Map map  = new HashMap();
        map.put("regiinvoiceDetailChaKanVoList",regiinvoiceDetailChaKanVoList);

        return map;
    }

    @Override
    public Map regiinvoiceSearch(RegiinvoiceSearchParam regiinvoiceSearchParam) {

        //分页查询处理
        Integer startPage = regiinvoiceSearchParam.getStartPage();
        Integer pageSize = regiinvoiceSearchParam.getPageSize();
        Integer  start = (startPage-1)*pageSize;
        regiinvoiceSearchParam.setStartPage(start);


        //满足条件的订单号
        List<String> orderCodes = regiinvoiceExtMapper.selectSearchOrderCode(regiinvoiceSearchParam);
        int total  = regiinvoiceExtMapper.selectSearchOrderCodeNum(regiinvoiceSearchParam);


        //获取订单中的所有信息
        if(total == 0){
            Map map  = new HashMap();
            map.put("regiinvoiceSearchVos",null);
            map.put("total",total);

            return map;
        }
        List<OrdersGaiVo> ordersGaiVoList  = orderExtMapper.selectMySellOrderGai(orderCodes);



      List<RegiinvoiceSearchVo> regiinvoiceSearchVos = new ArrayList<>();
       for(String code:orderCodes){
           //返回的对象
           RegiinvoiceSearchVo  regiinvoiceSearchVo = new RegiinvoiceSearchVo();


           String companyName = null;
           Statements statements = null;



           //返回对象中的集合
           List<RegiinvoiceSimpleVo> regiinvoiceSimpleVos = new ArrayList<>();
           for(OrdersGaiVo ordersGaiVo:ordersGaiVoList){
               if(code.equals(ordersGaiVo.getOrder().getCode())){


               RegiinvoiceSimpleVo simpleVo = new RegiinvoiceSimpleVo();


               simpleVo.setGoods(ordersGaiVo.getGoods());
               simpleVo.setOrderCode(ordersGaiVo.getOrder().getCode());
               simpleVo.setSettlement(ordersGaiVo.getSettlement());


               companyName = ordersGaiVo.getCertification().getCompany();
               statements = ordersGaiVo.getStatements();

               regiinvoiceSimpleVos.add(simpleVo);
               }

           }

           regiinvoiceSearchVo.setCompanyName(companyName);
           regiinvoiceSearchVo.setStatements(statements);
           regiinvoiceSearchVo.setRegiinvoiceSimpleVoList(regiinvoiceSimpleVos);

          //最外层返回集合
           regiinvoiceSearchVos.add(regiinvoiceSearchVo);
       }




        Map map  = new HashMap();
        map.put("regiinvoiceSearchVos",regiinvoiceSearchVos);
        map.put("total",total);

        return map;


    }
}
