package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.*;
import com.zc.shop.admin.service.GoodsService;
import com.zc.shop.admin.service.MessageManagerService;
import com.zc.shop.admin.service.OrderService;
import com.zc.shop.admin.vo.*;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

      @Autowired
    private StatementsExtMapper statementsExtMapper;


    @Autowired
    private SettlementExtMapper settlementExtMapper;

    @Autowired
    private RegiinvoiceExtMapper regiinvoiceExtMapper;


    @Autowired
    private RegiinvoicedetailExtMapper regiinvoicedetailExtMapper;


    @Autowired
    private MessageExtMapper messageExtMapper;


    @Autowired
    private MessageInfoExtMapper messageInfoExtMapper;


    @Autowired
    private MessageManagerService messageManagerService;


    @Autowired
    private GoodsService goodsService;




    @Override
      public Map mySellOrder(OrderSellSelectParam orderSellSelectParam, Integer userId) {

           //分页查询处理
            Integer startPage = orderSellSelectParam.getStartPage();
            Integer pageSize = orderSellSelectParam.getPageSize();
            Integer  start = (startPage-1)*pageSize ;
            orderSellSelectParam.setStartPage(start);

           //设置供应商id为当前用户id
            orderSellSelectParam.setSupplierId(userId);


           //获取满足条件的订单号（提前分页取出条数）
           List<String>  orderCodeGaiList = orderExtMapper.selectMySellOrderCodeGai(orderSellSelectParam);
           int total  = orderExtMapper.selectMySellOrderCodeGaiNum(orderSellSelectParam);


          if(total == 0){
              Map map  = new HashMap();
              map.put("ordersBackAllVoList",null);
              map.put("total",total);

              return map;
          }

            //获取订单中的所有信息
            List<OrdersGaiVo> ordersGaiVoList  = orderExtMapper.selectMySellOrderGai(orderCodeGaiList);
           // int total  = orderExtMapper.selectMySellOrderNumGai(orderSellSelectParam);


           //开始构造返回的对象
          List<OrdersBackAllVo> ordersBackAllVoList = new ArrayList<>();
           OrdersBackAllVo ordersBackAllVo = null;


            for(String ordercode:orderCodeGaiList){

                ordersBackAllVo = new OrdersBackAllVo();
                 //返回对象中的详情list
                List<OrderDetailAllVo> orderDetailAllVoList = new ArrayList<>();
                ordersBackAllVo.setOrderCode(ordercode);

                //初始化，防止下面用的时候空指针异常
                ordersBackAllVo.setWeightAll(new BigDecimal(0.0000));
                ordersBackAllVo.setPriceAll(new BigDecimal(0.00));


                for(OrdersGaiVo ordersGaiVo:ordersGaiVoList){
                   String orderCodeGet  = ordersGaiVo.getOrder().getCode();
                    if(orderCodeGet.equals(ordercode)){
                         //返回对象中的详情list中的对象
                        OrderDetailAllVo orderDetailAllVo = new OrderDetailAllVo();
                         //详情相关
                        orderDetailAllVo.setOrder(ordersGaiVo.getOrder());
                        orderDetailAllVo.setNumber(ordersGaiVo.getOrder().getRemainnumber());
                        orderDetailAllVo.setGoods(ordersGaiVo.getGoods());
                        orderDetailAllVo.setRegiinvoicedetail(ordersGaiVo.getRegiinvoicedetail());
                        orderDetailAllVo.setSettlement(ordersGaiVo.getSettlement());
                        orderDetailAllVo.setLadingList(ordersGaiVo.getLadingList());

                        //外层对象相关，其实查一遍就可以
                        ordersBackAllVo.setAdd_time(ordersGaiVo.getOrder().getCreatedAt());
                        ordersBackAllVo.setCertification(ordersGaiVo.getCertification());
                        ordersBackAllVo.setMargin(ordersGaiVo.getMargin());
                        ordersBackAllVo.setRegiinvoice(ordersGaiVo.getRegiinvoice());
                        ordersBackAllVo.setStatements(ordersGaiVo.getStatements());
                        ordersBackAllVo.setStatus(ordersGaiVo.getOrder().getStatus());
                        //计算总量，总重，总价

                        ordersBackAllVo.setNumAll(ordersBackAllVo.getNumAll()+ordersGaiVo.getOrder().getNum().intValue());
                        ordersBackAllVo.setWeightAll(ordersBackAllVo.getWeightAll().add(ordersGaiVo.getOrder().getWeight()));
                        ordersBackAllVo.setPriceAll(ordersBackAllVo.getPriceAll().add(new BigDecimal(ordersGaiVo.getOrder().getNum()).multiply(ordersGaiVo.getGoods().getShopPrice())));

                        orderDetailAllVoList.add(orderDetailAllVo);

                    }
                }

                //一个订单号对应多个详情

                ordersBackAllVo.setOrderDetailAllVoList(orderDetailAllVoList);
                ordersBackAllVoList.add(ordersBackAllVo);

            }




             Map map = new HashMap();

             map.put("ordersBackAllVoList",ordersBackAllVoList);
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


        //获取满足条件的订单号（提前分页取出条数）
        List<String>  orderCodeGaiList = orderExtMapper.selectMyBuyOrderCodeGai(orderBuySelectParam);
        int total  = orderExtMapper.selectMyBuyOrderCodeGaiNum(orderBuySelectParam);


        if(total == 0){
            Map map  = new HashMap();
            map.put("ordersBackAllVoList",null);
            map.put("total",total);

            return map;
        }
        //获取订单中的所有信息
        List<OrdersGaiVo> ordersGaiVoList  = orderExtMapper.selectMyBuyOrderGai(orderCodeGaiList);
       // int total  = orderExtMapper.selectMyBuyOrderNumGai(orderBuySelectParam);

        //开始构造返回的对象
        List<OrdersBackAllVo> ordersBackAllVoList = new ArrayList<>();
        OrdersBackAllVo ordersBackAllVo = null;



        for(String ordercode:orderCodeGaiList){

            ordersBackAllVo = new OrdersBackAllVo();
            //返回对象中的详情list
            List<OrderDetailAllVo> orderDetailAllVoList = new ArrayList<>();
            ordersBackAllVo.setOrderCode(ordercode);


            //初始化，防止下面用的时候空指针异常
            ordersBackAllVo.setWeightAll(new BigDecimal(0.0000));
            ordersBackAllVo.setPriceAll(new BigDecimal(0.00));


            for(OrdersGaiVo ordersGaiVo:ordersGaiVoList){
                String orderCodeGet  = ordersGaiVo.getOrder().getCode();
                if(orderCodeGet.equals(ordercode)){
                    //返回对象中的详情list中的对象
                    OrderDetailAllVo orderDetailAllVo = new OrderDetailAllVo();
                    //详情相关
                    orderDetailAllVo.setOrder(ordersGaiVo.getOrder());
                    orderDetailAllVo.setNumber(ordersGaiVo.getOrder().getRemainnumber());
                    orderDetailAllVo.setGoods(ordersGaiVo.getGoods());
                    orderDetailAllVo.setRegiinvoicedetail(ordersGaiVo.getRegiinvoicedetail());
                    orderDetailAllVo.setSettlement(ordersGaiVo.getSettlement());
                    orderDetailAllVo.setLadingList(ordersGaiVo.getLadingList());

                    //外层对象相关，其实查一遍就可以
                    ordersBackAllVo.setAdd_time(ordersGaiVo.getOrder().getCreatedAt());
                    ordersBackAllVo.setCertification(ordersGaiVo.getCertification());
                    ordersBackAllVo.setMargin(ordersGaiVo.getMargin());
                    ordersBackAllVo.setRegiinvoice(ordersGaiVo.getRegiinvoice());
                    ordersBackAllVo.setStatements(ordersGaiVo.getStatements());
                    ordersBackAllVo.setStatus(ordersGaiVo.getOrder().getStatus());


                    //计算总量，总重，总价
                    ordersBackAllVo.setNumAll(ordersBackAllVo.getNumAll()+ordersGaiVo.getOrder().getNum().intValue());
                    ordersBackAllVo.setWeightAll(ordersBackAllVo.getWeightAll().add(ordersGaiVo.getOrder().getWeight()));
                    ordersBackAllVo.setPriceAll(ordersBackAllVo.getPriceAll().add(new BigDecimal(ordersGaiVo.getOrder().getNum()).multiply(ordersGaiVo.getGoods().getShopPrice())));


                    orderDetailAllVoList.add(orderDetailAllVo);

                }
            }

            //一个订单号对应多个详情
            ordersBackAllVo.setOrderDetailAllVoList(orderDetailAllVoList);
            ordersBackAllVoList.add(ordersBackAllVo);

        }




        Map map = new HashMap();

        map.put("ordersBackAllVoList",ordersBackAllVoList);
        map.put("total",total);

        return map;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(OrderParam orderParam, Integer userId) {


        LocalDateTime now  = LocalDateTime.now();
        Integer status = orderParam.getStatus();

        //查询这个订单号下面有多少条订单数据
         List<Order> orders  = orderExtMapper.selectOrderByOrderCode(orderParam.getCode());

         if(orders == null){
             throw new BusinessException("从订单号下面无法查询到订单记录");
         }

        Integer supplierId = null;
        Integer buyUserId = null;

         for(Order order1: orders){
             supplierId = order1.getSupplierId();
             buyUserId = order1.getBuyId();

             order1.setUpdatedAt(now);
              order1.setStatus(status);

            int i = orderExtMapper.updateByPrimaryKeySelective(order1);

           //修改成功并且修改为0，订单作废，这时候需要去修改增加goods表商品数量(没有付款前可以作废订单)
            if(i>0 && status == 0){

                Order orderZuoFei = orderExtMapper.selectByPrimaryKey(order1.getId());

                //获取剩余重量和数量，加会goods表
                Short remainNum = orderZuoFei.getRemainnumber().shortValue();
                BigDecimal  remainWeight   = orderZuoFei.getRemaining();

                Integer goodId = orderZuoFei.getGoodsId();

              /*   Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);
            Short num = Integer.valueOf(goods.getGoodsNumber()+buyNum).shortValue();*/
                //修改商品数量
                /* int updateNumSuccess = goodsExtMapper.updateGoodsNum(goodId,num);*/
                int updateNumSuccess = goodsExtMapper.updateGoodsNumPlus(goodId,remainNum,remainWeight);
                if(!(updateNumSuccess >0)){
                    throw new BusinessException("商品数量重量还原失败");
                }

            }


        }

        //调用插入通知的service
       messageManagerService.addMessageByOrderCode(orderParam.getCode(),supplierId,buyUserId,userId,now);

        return orders.size();
    }

    @Override
    public List<Order> myOrderIndex(Integer userId) {


        return orderExtMapper.selectOrderBySupplierOrBuyUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createOrder(List<SupplierGoodsParam> supplierGoodsParamList, Integer userId) {

        //添加当前时间用
        LocalDateTime now = LocalDateTime.now();
        //生成订单编号用   100+日期时间+购买人id+供货商id+4位随机数
        String yyyyMMddHHmmss = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        //存放key:供货商id，订单号
        //Map<Integer,String> map = new HashMap<>();

        for(SupplierGoodsParam supplierGoodsParam :supplierGoodsParamList){


            //供货商id
            Integer supplierId = supplierGoodsParam.getSupplierId();


            //订单号
            String code = null;
            //有，取订单号
           /* for(Map.Entry<Integer, String> entry : map.entrySet()){
                if(supplierId.equals(entry.getKey())){
                    code = entry.getValue();
                }
            }*/
            //没有，生成订单号
            if(code == null){
                String random4 = Integer.valueOf((int)((Math.random()*9+1)*1000)).toString();
                code = "100"+ yyyyMMddHHmmss +userId.toString() +supplierId.toString() + random4;
                //存入map
               // map.put(supplierId,code);

            }

            List<GoodBuyParam> goodBuyParamList = supplierGoodsParam.getGoodBuyParam();

            //对同一供应商下的不同商品进行增减操作
            for(GoodBuyParam goodBuyParam : goodBuyParamList){

                //商品id
                Integer goodId =  goodBuyParam.getId();

            /*     //获取商品
                Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

                //看商品是否下架
                if( goods.getIsDelete() == 1){
                    //下架了
                    throw new BusinessException(ResultCode.GOODSISNOTONSALE);

                }*/

           /*
                //查看余量是否够（在这只比较数量，这就够了）
                Short remainNumber = goods.getRemainNumber();
                //买的数量
                Short buyNum = goodBuyParam.getNum().shortValue();
                //剩余重量
                BigDecimal remainWeight = goods.getRemainWeight();
                Short num = Integer.valueOf(remainNumber - buyNum).shortValue();
                //不够了
                if(num < 0 ){
                    throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
                }
                //计算单个商品重量
                Short goodsNumber = goods.getGoodsNumber();
                BigDecimal goodsWeight = goods.getGoodsWeight();
                BigDecimal oneWeight  = goodsWeight.divide(new BigDecimal(goodsNumber),4,RoundingMode.HALF_UP);


                //计算出剩余的重量
                remainWeight =remainWeight.subtract(oneWeight.multiply(new BigDecimal(buyNum)));

                if(num == 0){
                    remainWeight = new BigDecimal(0);
                }

                //修改goods表,修改剩余数量和剩余重量
                int goodsSuccess = goodsExtMapper.updateGoodsNum(goodId, num,remainWeight);
                if(goodsSuccess != 1){
                    throw new BusinessException("修改商品数量时失败："+"商品id为"+goodBuyParam.getId());

                }
*/

                //买的数量
                Short buyNum = goodBuyParam.getNum().shortValue();
                Goods goods  = goodsService.updateGoodNum(goodId,1,buyNum);

                //计算单个商品重量,这个很不好，最好在表里添加上
                Short goodsNumber = goods.getGoodsNumber();
                BigDecimal goodsWeight = goods.getGoodsWeight();
                BigDecimal oneWeight = goodsWeight.divide(new BigDecimal(goodsNumber), 4, RoundingMode.HALF_UP);




                //创建订单
                Order order = new Order();
                //订单买了多重
                BigDecimal remaining  = oneWeight.multiply(new BigDecimal(buyNum));




                order.setCode(code);
                order.setSupplierId(supplierId);
                order.setGoodsId(goodId);
                order.setBuyId(userId);

                order.setNum(buyNum.intValue());
                order.setWeight(remaining);
                order.setStatus(1);
                order.setCreatedAt(now);
                order.setUpdatedAt(now);
                order.setRemainnumber(buyNum.intValue());
                order.setRemaining(remaining);
                order.setRemark(supplierGoodsParam.getRemark());
                order.setPayment(supplierGoodsParam.getPayment());
                order.setMargin(supplierGoodsParam.getMargin());

                //返回的是添加成功的id
                Integer id = orderExtMapper.insertSelective(order);


                if(!(id > 0) ){
                    throw new BusinessException("创建订单时失败："+"商品id为"+goodBuyParam.getId());
                }

                //计算  (单价/单位重量)
                BigDecimal oneWeightPrice   = (goods.getShopPrice()).divide(oneWeight,2,RoundingMode.HALF_UP);


              //创建结算详细表数据
                Settlement settlement = new Settlement();
                settlement.setOrderCode(code);
                settlement.setGoodsId(goodId);
                settlement.setCreatedAt(now);
                settlement.setUpdatedAt(now);
                settlement.setOrderId(order.getId().longValue());
                settlement.setPrice(goods.getShopPrice());
                settlement.setWeightPrice(oneWeightPrice);

               int settlementId  = settlementExtMapper.insertSelective(settlement);




              //创建开票详细表数据
//                Regiinvoicedetail regiinvoicedetail = new Regiinvoicedetail();
//                regiinvoicedetail.setOrderCode(code);
//                regiinvoicedetail.setOrderId(order.getId());
//
//
//               int  regiinvoicedetailId =   regiinvoicedetailExtMapper.insertSelective(regiinvoicedetail);



            }

            //创建结算表数据
            Statements statements = new Statements();

            statements.setOrderCode(code);

            int statementsId = statementsExtMapper.insertSelective(statements);




            //创建开票表数据
//            Regiinvoice regiinvoice = new Regiinvoice();
//            regiinvoice.setOrderCode(code);
//
//            int  regiinvoiceId =   regiinvoiceExtMapper.insertSelective(regiinvoice);


        }


        return 1;
    }


    /* @Override
      @Transactional(rollbackFor = Exception.class)
      public int create(OrderCreateParam orderCreateParam,Integer userId) {

             //添加当前时间用
             LocalDateTime now = LocalDateTime.now();
            //生成订单编号用   100+日期时间+购买人id+供货商id+4位随机数
             String yyyyMMddHHmmss = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
             String code = null;
             String  random8 = Integer.valueOf((int)((Math.random()*9+1)*10000000)).toString();
             code = "100"+ yyyyMMddHHmmss + random8;




            for(ShopcartGaiVo shopcartGaiVo : orderCreateParam.getShopcartGaiVoList()){


                //供货商id
                Integer supplierId  = shopcartGaiVo.getGoods().getSuppliersId().intValue();

                //商品id
                Integer goodId = shopcartGaiVo.getGoods().getId();



                  //获取商品
                  Goods goods = goodsExtMapper.selectByPrimaryKey(goodId);

                  //看商品是否下架
                  if( goods.getIsDelete() == 1){
                  //下架了
                    throw new BusinessException(ResultCode.GOODSISNOTONSALE);

                  }


                  //查看余量是否够（在这只比较数量，这就够了）
                  Short remainNumber = goods.getRemainNumber();
                  //买的数量
                  Short buyNum = shopcartGaiVo.getNum().shortValue();
                  //剩余重量
                  BigDecimal remainWeight = goods.getRemainWeight();
                  Short num = Integer.valueOf(remainNumber - buyNum).shortValue();
                  //不够了
                  if(num < 0 ){
                        throw new BusinessException(ResultCode.GOODSNUMBERNOTENOUGH);
                  }
                  //计算单个商品重量
                  Short goodsNumber = goods.getGoodsNumber();
                  BigDecimal goodsWeight = goods.getGoodsWeight();
                  BigDecimal oneWeight  = goodsWeight.divide(new BigDecimal(goodsNumber));


                  //计算出剩余的重量
                  remainWeight =remainWeight.subtract(oneWeight.multiply(new BigDecimal(num)));

                  //修改goods表,修改剩余数量和剩余重量
                  int goodsSuccess = goodsExtMapper.updateGoodsNum(goodId, num,remainWeight);
                  if(goodsSuccess != 1){
                       throw new BusinessException("修改商品数量时失败："+shopcartGaiVo.getGoods().getGoodsName());

                  }


                  //创建订单
                  Order order = new Order();
                  //订单买了多重
                  BigDecimal remaining  = oneWeight.multiply(new BigDecimal(buyNum));




                 order.setCode(code);
                 order.setSupplierId(supplierId.intValue());
                 order.setGoodsId(goodId);
                 order.setBuyId(userId);

                 order.setNum(buyNum.intValue());
                 order.setWeight(remaining);
                 order.setStatus(1);
                 order.setCreatedAt(now);
                 order.setUpdatedAt(now);
                 order.setRemainnumber(buyNum.intValue());
                 order.setRemaining(remaining);
                 order.setRemark(orderCreateParam.getRemark());
                order.setPayment(orderCreateParam.getPayment());
                order.setMargin(orderCreateParam.getMargin());


                  int i = orderExtMapper.insertSelective(order);

                  if(!(i > 0) ){
                        throw new BusinessException("创建订单时失败："+shopcartGaiVo.getGoods().getGoodsName());
                  }


                  Short  shopcartId  = shopcartGaiVo.getShopcart().getId();
                  //删除购物车
                  int deleteSuccess = shopcartExtMapper.deleteByPrimaryKey(shopcartId);
                  if(deleteSuccess != 1){
                         throw  new  BusinessException("删除购物车表失败："+shopcartId);
                  }

            }


            //正常走完就是成功

            return 1;
      }
*/

}
