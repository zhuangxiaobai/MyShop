package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.zc.shop.admin.dto.OrderBuySelectParam;
import com.zc.shop.admin.dto.OrderParam;
import com.zc.shop.admin.dto.OrderSellSelectParam;
import com.zc.shop.admin.dto.ShopcartParam;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.OrderExtMapper;
import com.zc.shop.admin.mapper.ShopcartExtMapper;
import com.zc.shop.admin.service.OrderService;
import com.zc.shop.admin.vo.OrdersAllVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {


      @Autowired
      private OrderExtMapper orderExtMapper;

      @Autowired
      private GoodsExtMapper goodsExtMapper;

      @Autowired
      private ShopcartExtMapper shopcartExtMapper;


      @Override
      public Map mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId) {

           //分页查询处理
            Integer startPage = orderSellSelectParam.getStartPage();
            Integer pageSize = orderSellSelectParam.getPageSize();
            Integer  start = (startPage-1)*pageSize ;
            orderSellSelectParam.setStartPage(start);
            orderSellSelectParam.setSupplierId(userId);
            //获取订单中的所有信息
            List<OrdersAllVo> orderGoodsVo  = orderExtMapper.selectMySellOrder(orderSellSelectParam);
            int total  = orderExtMapper.selectMySellOrderNum(orderSellSelectParam);

             Map map = new HashMap();

             map.put("orderGoodsVoList",orderGoodsVo);
             map.put("total",total);

            return map;
      }


    @Override
    public Map myBuyOrder(OrderBuySelectParam orderBuySelectParam, Integer userId) {
        //分页查询处理
        Integer startPage = orderBuySelectParam.getStartPage();
        Integer pageSize = orderBuySelectParam.getPageSize();
        Integer  start = (startPage-1)*pageSize ;
        orderBuySelectParam.setStartPage(start);
        orderBuySelectParam.setBuyId(userId);
        //获取订单中的所有信息
        List<OrdersAllVo> orderGoodsVo  = orderExtMapper.selectMyBuyOrder(orderBuySelectParam);
        int total  = orderExtMapper.selectMyBuyOrderNum(orderBuySelectParam);


        Map map = new HashMap();

        map.put("orderGoodsVoList",orderGoodsVo);
        map.put("total",total);

        return map;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateShopcart(OrderParam orderParam) {



          Order order = new Order();
        BeanUtil.copyProperties(orderParam,order);
        order.setUpdatedAt(LocalDateTime.now());
        int i = orderExtMapper.updateByPrimaryKeySelective(order);
        //修改成功并且修改为15，订单作废，这时候需要去修改增加goods表商品数量(没有付款前可以作废订单)
        if(i>0 && order.getStatus().toString().equals("15")){

            Order orderZuoFei = orderExtMapper.selectByPrimaryKey(order.getId());

           Short buyNum = orderZuoFei.getNum().shortValue();
           Integer goodId = orderZuoFei.getGoodsId();

         /*   Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);
            Short num = Integer.valueOf(goods.getGoodsNumber()+buyNum).shortValue();*/
            //修改商品数量
           /* int updateNumSuccess = goodsExtMapper.updateGoodsNum(goodId,num);*/
            int updateNumSuccess = goodsExtMapper.updateGoodsNumPlus(goodId,buyNum);
            if(!(updateNumSuccess >0)){
                throw new BusinessException("商品数量还原失败");
            }

        }

        return i;
    }


      @Override
      @Transactional(rollbackFor = Exception.class)
      public int create(List<ShopcartParam> shopcartList) {

            //添加当前时间用
            LocalDateTime now = LocalDateTime.now();
            //生成订单编号用   100+日期时间+购买人id+供货商id+4位随机数
            String yyyyMMddHHmmss = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            //存放key:供应商id  value:订单号
            Map<Short,String> map = new HashMap();




            for(ShopcartParam shopcart : shopcartList){

                  Short shopcartId = shopcart.getId();

                  Integer userId = shopcart.getUserId();

                  Short  supplierId  = shopcart.getSuppliersId();

                  //取得json格式的goods字段
                  String goodsParam = shopcart.getGoods();
                  JSONObject goodsJson = JSONObject.parseObject(goodsParam);
                  Integer goodId = (Integer) goodsJson.get("good_id");
                  Integer buyNum = (Integer) goodsJson.get("num");

                  //获取商品
                  Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

                  //看商品是否下架
                  if( goods.getIsOnSale() == 0){
                  //下架了
                    throw new BusinessException(ResultCode.GOODSISNOTONSALE);

                  }


                  //查看余量是否够
                  Short goodsNumber = goods.getGoodsNumber();
                  Short num = Integer.valueOf(goodsNumber - buyNum).shortValue();
                  //不够了
                  if(num < 0 ){
                        throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
                  }


                  //修改goods表对应的数量
                  int goodsSuccess = goodsExtMapper.updateGoodsNum(goodId, num);
                  if(goodsSuccess != 1){
                       throw new BusinessException("修改商品数量时失败："+shopcartId);

                  }


                  //创建订单
                  Order order = new Order();

                  BigDecimal goodsWeight = goods.getGoodsWeight();
                  BigDecimal remaining  = goodsWeight.multiply(new BigDecimal(buyNum));

                  //订单号
                  String code = null;
                  //有，取订单号
                  for(Map.Entry<Short, String> entry : map.entrySet()){
                       if(supplierId.equals(entry.getKey())){
                          code = entry.getValue();
                       }
                  }

                  //没有有，生成订单号
                 if(code == null){

                    String  random4 = Integer.valueOf((int)((Math.random()*9+1)*1000)).toString();

                     code = "100"+ yyyyMMddHHmmss +userId.toString() +supplierId.toString() + random4;

                 }





                  order.setCode(code);
                 order.setSupplierId(supplierId.intValue());
                 order.setGoodsId(goodId);
                 order.setBuyId(userId);
                 order.setNum(buyNum);
                 order.setWeight(goodsWeight);
                 order.setStatus(0);
                 order.setCreatedAt(now);
                 order.setUpdatedAt(now);
                 order.setRemaining(remaining);
                 order.setRemark(shopcart.getRemark());


                  int i = orderExtMapper.insertSelective(order);

                  if(!(i > 0) ){
                        throw new BusinessException("创建订单时失败："+shopcartId);
                  }


                  //删除购物车
                  int deleteSuccess = shopcartExtMapper.deleteByPrimaryKey(shopcartId);
                  if(deleteSuccess != 1){
                         throw  new  BusinessException("删除购物车表失败："+shopcartId);
                  }

            }


            //正常走完就是成功

            return 1;
      }


}
