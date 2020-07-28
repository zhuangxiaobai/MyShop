package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.LadingBuySelectParam;
import com.zc.shop.admin.dto.LadingCreateParam;
import com.zc.shop.admin.dto.LadingParam;
import com.zc.shop.admin.dto.LadingSellSelectParam;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.LadingExtMapper;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.service.LadingService;
import com.zc.shop.admin.vo.LadingAllVo;
import com.zc.shop.admin.vo.OrdersAllVo;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Lading;
import com.zc.shop.mbg.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LadingServiceImpl implements LadingService {

    @Autowired
    private GoodsExtMapper goodsExtMapper;


    @Autowired
    private OrderExtMapper orderExtMapper;

    @Autowired
    private LadingExtMapper ladingExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(LadingCreateParam ladingCreateParam, Integer userId) {


        Lading lading  = new Lading();

        BeanUtil.copyProperties(ladingCreateParam,lading);
       lading.setStatus(0);

       LocalDateTime   localDateTime=  LocalDateTime.now();
       lading.setCreatedAt(localDateTime);
       lading.setUpdatedAt(localDateTime);


       //获取提单数量
       BigDecimal  ladingWeight =  lading.getWeight();

       //订单表
       Integer  orderId = lading.getOrderId();

       //查询订单表看看余量够不够

        Order orderBack = orderExtMapper.selectByPrimaryKey(orderId.longValue());
        //余量
        BigDecimal remaining = orderBack.getRemaining();

        //余量小于提单量
        if(remaining.compareTo(ladingWeight)== -1 ){

          throw new BusinessException("订单表余量不足");

        }

        //去修改order表余量
         remaining = remaining.subtract(ladingWeight);
         orderBack.setRemaining(remaining);
         orderBack.setUpdatedAt(localDateTime);


        int i = orderExtMapper.updateByPrimaryKeySelective(orderBack);
        if(!(i > 0)){

          throw new BusinessException("修改订单表余量失败");
        }

         //提单号    110+ yyyyMMddHHmmss +4位随机数 + orderId
        String  yyyyMMddHHmmss = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String  random4 = Integer.valueOf((int)((Math.random()*9+1)*1000)).toString();
        String code = "110"+yyyyMMddHHmmss+ random4  +orderId;
        lading.setCode(code);
        //0-待确认
        lading.setStatus(0);



       //创建提单
        int insertId = ladingExtMapper.insertSelective(lading);

        if( !(insertId>0)){
            throw new BusinessException("创建提单表数据失败");

        }





        return 1;
    }

    @Override
    public int updateLadingStatus(LadingParam ladingParam) {

        Lading lading = new Lading();
        BeanUtil.copyProperties(ladingParam,lading);
        lading.setUpdatedAt(LocalDateTime.now());

        int i = ladingExtMapper.updateByPrimaryKeySelective(lading);
        //修改成功并且修改为16，订单作废，这时候需要去修改增加order表商品余量(没有提货可以作废提单单)
        if(i>0 && lading.getStatus().toString().equals("16")){

            Lading ladingZuoFei = ladingExtMapper.selectByPrimaryKey(lading.getId());

            BigDecimal tiNum = ladingZuoFei.getWeight();
            Integer orderId = ladingZuoFei.getOrderId();

         /*   Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);
            Short num = Integer.valueOf(goods.getGoodsNumber()+buyNum).shortValue();*/
            //修改商品数量
            /* int updateNumSuccess = goodsExtMapper.updateGoodsNum(goodId,num);*/
            int updateNumSuccess = orderExtMapper.updateOrderRemaing(orderId,tiNum);
            if(!(updateNumSuccess >0)){
                throw new BusinessException("订单余量还原失败");
            }

        }

        return i;


    }

    @Override
    public List<LadingAllVo> mySellLading(LadingSellSelectParam ladingSelectParam, Integer userId) {







        return null;
    }

    @Override
    public List<LadingAllVo> myBuyLading(LadingBuySelectParam ladingSelectParam, Integer userId) {



        //分页查询处理
        Integer startPage = ladingSelectParam.getStartPage();
        Integer pageSize = ladingSelectParam.getPageSize();
        Integer  start = (startPage-1)*pageSize +1;
        ladingSelectParam.setStartPage(start);
        ladingSelectParam.setBuyId(userId);
        //获取订单中的所有信息
        List<LadingAllVo> ladingAllVos  = ladingExtMapper.selectMyBuyLading(ladingSelectParam);


        return ladingAllVos;



    }
}
