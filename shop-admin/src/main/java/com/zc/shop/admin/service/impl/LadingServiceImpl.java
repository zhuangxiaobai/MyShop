package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.*;
import com.zc.shop.admin.service.LadingService;
import com.zc.shop.admin.service.MessageManagerService;
import com.zc.shop.admin.util.ListPageUtil;
import com.zc.shop.admin.vo.LadingAllVo;
import com.zc.shop.admin.vo.ShiTiChuKuVo;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LadingServiceImpl implements LadingService {

    @Autowired
    private GoodsExtMapper goodsExtMapper;


    @Autowired
    private OrderExtMapper orderExtMapper;

    @Autowired
    private LadingExtMapper ladingExtMapper;

    @Autowired
    private CertificationExtMapper certificationExtMapper;

    @Autowired
    private StatementsExtMapper statementsExtMapper;


    @Autowired
    private SettlementExtMapper settlementExtMapper;

    @Autowired
    private MessageManagerService messageManagerService;


    /**
     * 作废
     */
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
    @Transactional(rollbackFor = Exception.class)
    public int updateLadingStatus(LadingParam ladingParam, Integer userId) {

        LocalDateTime now  = LocalDateTime.now();
        Integer status = ladingParam.getStatus();

        //查询这个提单号下面有多少条提单数据
        List<Lading> ladings = ladingExtMapper.selectLadingByLadingCode(ladingParam.getLadingCode());
        //获取供货商id，用于通知时候的填写供货商id用
        Integer supplierId = null;

        String orderCode = null;
        //为了下面提单发送通知的时候去查数据库获取买方id用
        Integer orderIdForMessage = null;
        for(Lading lading1: ladings){

            supplierId = lading1.getSupplierId();
            orderIdForMessage = lading1.getOrderId();

            lading1.setUpdatedAt(now);
            lading1.setStatus(status);

            int i = ladingExtMapper.updateByPrimaryKeySelective(lading1);

            //修改成功并且修改为0，提单作废，这时候需要去修改增加order表数量
            if(i>0 && status == 0){

                Lading ladingZuoFei = ladingExtMapper.selectByPrimaryKey(lading1.getId());

                //获取剩余重量和数量，加会goods表
                Integer remainNum = ladingZuoFei.getNum();
                BigDecimal  remainWeight   = ladingZuoFei.getWeight();

                Long orderId = ladingZuoFei.getOrderId().longValue();

                //修改商品数量
                int updateNumSuccess = orderExtMapper.updateOrderNumAndWeight(orderId,remainNum,remainWeight);
                if(!(updateNumSuccess >0)){
                    throw new BusinessException("订单数量以及重量还原失败");
                }

            }
            //成功修改并修改为4，提单完成（买家确认收货的时候触发，需要修改订单中的剩余数量和剩余重量）
            else if(i>0 && status == 4){
                //获取提单表记录
                Lading ladingWanCheng = ladingExtMapper.selectByPrimaryKey(lading1.getId());
                Integer orderId = ladingWanCheng.getOrderId();
                orderCode = ladingWanCheng.getOrderCode();
                //买家实际提货量
                Integer   wanchengnum= ladingWanCheng.getRealNum();
                BigDecimal  wanchengweight = ladingWanCheng.getRealWeight();
                //买家申请的提货量
                Integer  numshengqing = ladingWanCheng.getNum();
                BigDecimal weightshengqing = ladingWanCheng.getWeight();
                //差值
                 Integer numduoti = wanchengnum - numshengqing;
                 BigDecimal weightduoti = wanchengweight.subtract(weightshengqing);



                Order orderNeedXiuGai = orderExtMapper.selectByPrimaryKey(orderId.longValue());
                orderNeedXiuGai.setRemainnumber(orderNeedXiuGai.getRemainnumber()-numduoti);
                orderNeedXiuGai.setRemaining(orderNeedXiuGai.getRemaining().subtract(weightduoti));
                orderNeedXiuGai.setUpdatedAt(now);

                //修改对应订单数据
                int updateSuccess  = orderExtMapper.updateByPrimaryKeySelective(orderNeedXiuGai);
                if(!(updateSuccess >0)){
                    throw new BusinessException("修改订单表余量失败");
                }

            }

        }


      //4为提单中的买家确认收货，这时候需要判断此提单号关联的订单号下的所有商品是否已经被提完，提完就去修改订单状态为
      //  5待结算，没提完就不用修改，依旧为4
       if(ladingParam.getStatus() == 4){

          //取结算详情表中数据与订单表一一对应，比对实提数量与实提重量，所有实提重量订单表>=购买重量 可算都完成
           List<Order> orderList = orderExtMapper.selectOrderByOrderCode(orderCode);

           List<Settlement> settlementList = settlementExtMapper.selectByOrderCode(orderCode);

           //计算同一订单号下提完的商品数有多少
           int count = 0;
           //订单变化时候的买方id，用在本{}中
           Integer buyUserId  = null;
          for(Order order :orderList){

              buyUserId = order.getBuyId();

               long orderId  = order.getId();
               Integer  buyNum = order.getNum();
               BigDecimal buyWeight = order.getWeight();
              for(Settlement settlement :settlementList){
                 long orderIds  = settlement.getOrderId();
                 if(orderId == orderIds){
                     Integer shitiNum  = settlement.getNum();
                     BigDecimal shitiWeight = settlement.getWeight();
                     //比较
                    if((shitiNum.compareTo(buyNum) >= 0 ) &&
                            (shitiWeight.compareTo(buyWeight) >=0)){
                         count ++;

                    }


                 }



              }




          }

         if(count==orderList.size()){
              //修改所有为此订单号的记录状态为5（待结算）

             for(Order order :orderList) {
                   order.setStatus(5);
                   order.setUpdatedAt(now);
                 int i = orderExtMapper.updateByPrimaryKeySelective(order);


             }

         }

         //订单状态发生变化,发生订单通知
           messageManagerService.addMessageByOrderCode(orderCode,supplierId,buyUserId,userId,now);



       }else{
            //为了去获取订单数据中买方id用的
           Order order = orderExtMapper.selectByPrimaryKey(orderIdForMessage.longValue());

           //只有提单发生了变化
           //提单发生改变通知，发生提单通知
           messageManagerService.addMessageByTiCode(ladingParam.getLadingCode(),supplierId, order.getBuyId(),userId,now);



       }





        return ladings.size();


    }

    @Override
    public Map mySellLading(LadingSellSelectParam ladingSelectParam, Integer userId) {

        //分页查询处理
        Integer startPage = ladingSelectParam.getStartPage();
        Integer pageSize = ladingSelectParam.getPageSize();
        Integer  start = (startPage-1)*pageSize ;
        ladingSelectParam.setStartPage(start);
        ladingSelectParam.setSupplierId(userId);
        //获取订单中的所有信息
        List<LadingAllVo> ladingAllVos  = ladingExtMapper.selectMySellLading(ladingSelectParam);
        int total  = ladingExtMapper.selectMySellLadingNum(ladingSelectParam);


        Map map = new HashMap();

       map.put("ladingAllVosList",ladingAllVos);
       map.put("total",total);



        return map;
    }

    @Override
    public Map myBuyLading(LadingBuySelectParam ladingSelectParam, Integer userId) {



        //分页查询处理
        Integer startPage = ladingSelectParam.getStartPage();
        Integer pageSize = ladingSelectParam.getPageSize();
        Integer  start = (startPage-1)*pageSize ;
        ladingSelectParam.setStartPage(start);
        ladingSelectParam.setBuyId(userId);

        //通过userId获取公司名称
        Certification certification = certificationExtMapper.selectByUserId(userId);
        String companyName = certification.getCompany();
        ladingSelectParam.setCompanyName(companyName);

        //获取订单中的所有信息
        List<LadingAllVo> ladingAllVos  = ladingExtMapper.selectMyBuyLading(ladingSelectParam);
        int total  = ladingExtMapper.selectMyBuyLadingNum(ladingSelectParam);


        Map map = new HashMap();

        map.put("ladingAllVosList",ladingAllVos);
        map.put("total",total);



        return map;



    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createLadings(List<Lading> ladingList, Integer userId) {

        //每一次创建提单，提单号相同，也可能提一个订单下面的多个商品(每一次提单是提同一订单下的一个或者多个商品)

        String ladingCode = null;
        LocalDateTime now  = LocalDateTime.now();
        String yyyyMMddHHmmss = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random6 = Integer.valueOf((int)((Math.random()*9+1)*100000)).toString();
        //提单号设置为T100+
        ladingCode =  "T100"+yyyyMMddHHmmss +random6;

        for(Lading lading : ladingList){

            System.out.println(lading.getInformation());

//            Object parse = JSONArray.parse(lading.getInformation());
//            String aaa = (String).get("name");
//            System.out.println(aaa);

            //提单状态 1.整单  2.分批  3.过户
            Integer  type = lading.getType();
            //获取订单号
            //String code  = lading.getOrderCode();

            lading.setCode(ladingCode);
            lading.setCreatedAt(now);
            lading.setUpdatedAt(now);
            lading.setLadingedAt(now);

            if(type==1 || type == 2){

                int i = ladingExtMapper.insertSelective(lading);

                if(!(i>0)){
                  throw  new BusinessException("添加提单表时出现异常:"+"商品id为"+lading.getGoodsId());
                }


            }else if(type == 3){

                String guohu = lading.getGuohu();


                Certification certification  = certificationExtMapper.selectByCompanyName(guohu);
                //公司认证通过才可以添加
                if(certification.getStatus() ==1){

                    int i = ladingExtMapper.insertSelective(lading);
                    if(!(i>0)){
                        throw  new BusinessException("添加过户提单表时出现异常:"+"商品id为"+lading.getGoodsId());
                    }

                }else{

                  throw new BusinessException("所选公司还未通过验证");
                }

            }

        }

        return ladingList.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int shiTiParam(List<ShiTiParam> shiTiParamList) {
       //录入实提需要是同一订单编号下面的订单商品详情记录
        LocalDateTime now  = LocalDateTime.now();
        String  orderCode = null;

        for(ShiTiParam shiTiParam: shiTiParamList){
            long ladingId = shiTiParam.getLadingId();


            int i  = ladingExtMapper.updateShiTi(shiTiParam);

         /*   if( i > 0){
                throw new BusinessException("修改实提失败,提单表id为:"+shiTiParam.getLadingId());
            }
         */

            //获取orderCode
            Lading lading = ladingExtMapper.selectByPrimaryKey(ladingId);
            Integer orderId = lading.getOrderId();
            orderCode = lading.getOrderCode();

            //修改结算详情表的数据（重量+，数量+，结算金额+，日期改，）

            Integer shiTiNum = shiTiParam.getShiTiNum();
            BigDecimal shiTiWeight = shiTiParam.getShiTiWeight();

            int j = settlementExtMapper.updateWeightAndNum(orderId.longValue(),shiTiNum,shiTiWeight,now);




        }

         //通过orderCode获取所有结算详情数据
        List<Settlement> settlementList  = settlementExtMapper.selectByOrderCode(orderCode);

        int sumNum = 0;
        BigDecimal weightSum = new BigDecimal(0);
        BigDecimal priceSum = new BigDecimal(0);
        for(Settlement settlement:settlementList){

            sumNum+=settlement.getNum();
            weightSum=weightSum.add(settlement.getWeight());
            priceSum=priceSum.add(settlement.getWeight());

        }



         //修改结算总表，把下面的都加起来
         int i = statementsExtMapper.updateWeightAndNum(orderCode,sumNum,weightSum,priceSum);




        return shiTiParamList.size();
    }

    @Override
    public Map selectShitichuku(ShiTiChuKuParam shiTiChuKuParam) {


        //获取提单表信息
        List<LadingAllVo> ladingAllVos  = ladingExtMapper.selectShiTiChuKu(shiTiChuKuParam);
        //返回前端的对象集合
        List<ShiTiChuKuVo> shiTiChuKuVos = new ArrayList<>();
        Set<String>  codeSet = new HashSet();
        for(LadingAllVo ladingAllVo:ladingAllVos){

          codeSet.add(ladingAllVo.getLading().getCode());

        }

        //有多少个提单号,就有多少个返回对象
        for(String code:codeSet){

            ShiTiChuKuVo shiTiChuKuVo = new ShiTiChuKuVo();


            List<LadingAllVo> ladingAllVos1 = new ArrayList<>();
            for(LadingAllVo ladingAllVo:ladingAllVos){
                String ladingCode  = ladingAllVo.getLading().getCode();
                if(code.equals(ladingCode)){
                    ladingAllVos1.add(ladingAllVo);
                }
            }
            shiTiChuKuVo.setLadingAllVos(ladingAllVos1);

            shiTiChuKuVos.add(shiTiChuKuVo);

        }
       //对返回集合分页
        //分页处理
        Integer startPage = shiTiChuKuParam.getStartPage();
        Integer pageSize = shiTiChuKuParam.getPageSize();
//        Integer  start = (startPage-1)*pageSize ;
//        shiTiChuKuParam.setStartPage(start);


        List<ShiTiChuKuVo> shiTiChuKuVosPage = ListPageUtil.page(shiTiChuKuVos, startPage, pageSize);


        Map map = new HashMap();
        map.put("shiTiChuKuVosPage",shiTiChuKuVosPage);
        map.put("total",codeSet.size());


        return map;
    }
}
