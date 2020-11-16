package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.*;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.InventoryExtMapper;
import com.zc.shop.admin.mapper.InventoryLogExtMapper;
import com.zc.shop.admin.service.InventoryService;
import com.zc.shop.admin.vo.RuKuCaiGouChukuXiaoShouVo;
import com.zc.shop.admin.vo.RuKuChuKuVo;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Inventory;
import com.zc.shop.mbg.po.InventoryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    private InventoryExtMapper inventoryExtMapper;

    @Autowired
    private InventoryLogExtMapper inventoryLogExtMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createInventory(List<Inventory> inventoryList, Integer userId) {

        LocalDateTime now  = LocalDateTime.now();

        String name = null;
        String guige = null;
        String caizhi = null;

        for(Inventory inventory:inventoryList){
            //品名
             name = inventory.getGoodsName();
            //规格
             guige = inventory.getGuige();
            //材质
             caizhi = inventory.getCaizhi();

            //查看库存表里面有没有此商品

            Inventory inventoryExist = inventoryExtMapper.selectByNameGuigeCaizhi(name,guige,caizhi);
            //有，修改数量
            if(inventoryExist != null){

               Long inventoryExistId  = inventoryExist.getId();
                Integer number = inventory.getGoodsNumber();
                BigDecimal weight = inventory.getGoodsWeight();


                //修改这个商品的数量和重量
                int i = inventoryExtMapper.updateNumWeight(inventoryExistId,number,weight);
                if (!(i > 0)) {
                    throw new BusinessException("修改库存表出现异常");

                }

                inventory.setId(inventoryExistId);

            }
            //创建新的
            else {


                inventory.setSuppliersId(userId);
                int i = inventoryExtMapper.insertSelective(inventory);
                if (!(i > 0)) {
                    throw new BusinessException("添加库存表出现异常");

                }

            }
                Long inventoryId = inventory.getId();

                InventoryLog inventoryLog = new InventoryLog();
                inventoryLog.setCreatedAt(now);
                inventoryLog.setInvId(inventoryId);
                inventoryLog.setType(0);
                inventoryLog.setInuId(userId);
                inventoryLog.setNum(inventory.getGoodsNumber());
                inventoryLog.setWeight(inventory.getGoodsWeight());


                int i1 = inventoryLogExtMapper.insertSelective(inventoryLog);
                if (!(i1 > 0)) {
                    throw new BusinessException("添加库存记录表出现异常");

                }


        }




        return inventoryList.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createChuku(List<ChuKuParam> chuKuParams, Integer userId) {

        LocalDateTime now  = LocalDateTime.now();

        String name = null;
        String guige = null;
        String caizhi = null;
       for(ChuKuParam chuKuParam:chuKuParams){
           //品名
           name = chuKuParam.getName();
           //规格
           guige = chuKuParam.getGuige();
           //材质
           caizhi = chuKuParam.getCaizhi();


          //查询库存表获取id
           Inventory inventoryExist = inventoryExtMapper.selectByNameGuigeCaizhi(name,guige,caizhi);

           if(inventoryExist == null) {
               //没有，异常
               throw  new  BusinessException("库存表中不存在此商品");

           }

               Integer num = chuKuParam.getNum();
               BigDecimal weight = chuKuParam.getWeight();

             //查看数量和重量是否足够
              Integer existNum = inventoryExist.getGoodsNumber();
              BigDecimal existWeight = inventoryExist.getGoodsWeight();
             //得到剩余量
               Integer numhaving  = existNum-num;
               BigDecimal weighthaving = existWeight.subtract(weight);


               if((numhaving < 0) || (weighthaving.compareTo(new BigDecimal(0))<0) ){
                   throw  new BusinessException("库存量不足");
               }

              //修改这个商品的数量和重量
                inventoryExist.setGoodsNumber(numhaving);
                inventoryExist.setGoodsWeight(weighthaving);
               int i = inventoryExtMapper.updateByPrimaryKeySelective(inventoryExist);
               if (!(i > 0)) {
                   throw new BusinessException("修改库存表出现异常");

               }

            //添加库存记录表

           Long inventoryExistId  = inventoryExist.getId();
           InventoryLog inventoryLog = new InventoryLog();
           inventoryLog.setCreatedAt(now);
           inventoryLog.setInvId(inventoryExistId);
           inventoryLog.setType(1);
           inventoryLog.setInuId(userId);
           inventoryLog.setNum(chuKuParam.getNum());
           inventoryLog.setWeight(chuKuParam.getWeight());


           int i1 = inventoryLogExtMapper.insertSelective(inventoryLog);
           if (!(i1 > 0)) {
               throw new BusinessException("添加库存记录表出现异常");

           }




       }





        return chuKuParams.size();
    }

    @Override
    public Map inventoryList(InventorySelectParam inventorySelectParam, Integer userId) {


        inventorySelectParam.setSupplierId(userId);

        List<Inventory>  inventories = inventoryExtMapper.selectByInventoryParam(inventorySelectParam);
        Integer  total = inventoryExtMapper.selectByInventoryParamNum(inventorySelectParam);

        Map map  = new HashMap();

        map.put("inventories",inventories);
        map.put("total",total);


        return map;
    }

    @Override
    public Map ruKuList(RuKuChuKuParam ruKuChuKuParam, Integer userId) {



        //分页查询处理
        Integer startPage = ruKuChuKuParam.getStartPage();
        Integer pageSize = ruKuChuKuParam.getPageSize();
        Integer  start = (startPage-1)*pageSize ;
        ruKuChuKuParam.setStartPage(start);


        ruKuChuKuParam.setSupplierId(userId);

        List<RuKuChuKuVo>  ruKuChuKuVos = inventoryLogExtMapper.selectRuKuByParam(ruKuChuKuParam);
        Integer  total = inventoryLogExtMapper.selectRuKuNumByParam(ruKuChuKuParam);

        Map map  = new HashMap();

        map.put("ruKuChuKuVos",ruKuChuKuVos);
        map.put("total",total);


        return map;



    }

    @Override
    public Map chuKuList(RuKuChuKuParam ruKuChuKuParam, Integer userId) {


        //分页查询处理
        Integer startPage = ruKuChuKuParam.getStartPage();
        Integer pageSize = ruKuChuKuParam.getPageSize();
        Integer  start = (startPage-1)*pageSize ;
        ruKuChuKuParam.setStartPage(start);


        ruKuChuKuParam.setSupplierId(userId);

        List<RuKuChuKuVo>  chuKuChuKuVos = inventoryLogExtMapper.selectChuKuByParam(ruKuChuKuParam);
        Integer  total = inventoryLogExtMapper.selectChuKuNumByParam(ruKuChuKuParam);

        Map map  = new HashMap();

        map.put("ruKuChuKuVos",chuKuChuKuVos);
        map.put("total",total);


        return map;


    }

    @Override
    public Map ruKuCaiGouList(RuKuCaiGouParam ruKuCaiGouParam, Integer userId) {


       //入库查自己的已经交易完成的采购单，订单买家是自己
        ruKuCaiGouParam.setBuyId(userId);

        List<RuKuCaiGouChukuXiaoShouVo> ruKuCaiGouChukuXiaoShouVoList  = inventoryLogExtMapper.selectRuKuCaiGouList(ruKuCaiGouParam);




        Map map  = new HashMap();

        map.put("ruKuCaiGouChukuXiaoShouVoList",ruKuCaiGouChukuXiaoShouVoList);


        return map;
    }

    @Override
    public Map chuKuXiaoShouList(ChuKuXiaoShouParam chuKuXiaoShouParam, Integer userId) {

        //入库查自己的已经交易完成的销售单，订单卖家家是自己
         chuKuXiaoShouParam.setSupplierId(userId);

        List<RuKuCaiGouChukuXiaoShouVo> ruKuCaiGouChukuXiaoShouVoList  = inventoryLogExtMapper.selectChuKuXiaoShouList(chuKuXiaoShouParam);


        Map map  = new HashMap();

        map.put("ruKuCaiGouChukuXiaoShouVoList",ruKuCaiGouChukuXiaoShouVoList);


        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int inventoryAddGoods(List<GoodsCreateParam> goodsCreateParams, Integer supplierId) {

        LocalDateTime now = LocalDateTime.now();
        String name= null;
        String guige = null;
        String caizhi = null;

        for(GoodsCreateParam goodsCreateParam:goodsCreateParams){

          //查询商品表同种商品是否存在，存在修改数量，不存在创建

          name = goodsCreateParam.getGoodsName();
          guige = goodsCreateParam.getGuige();
          caizhi = goodsCreateParam.getCaizhi();

          //查询的商品表的（不管上下架的）
          Goods goodExist = goodsExtMapper.selectByNameGuigeCaizhi(supplierId.shortValue(),name,guige,caizhi);

          //添加
          if(goodExist != null){
            Short numberAdd   = goodsCreateParam.getGoodsNumber();
            BigDecimal weightAdd  =  goodsCreateParam.getGoodsWeight();
            Integer goodId = goodExist.getId();

            goodsExtMapper.updateGoodsNumAll(goodId,numberAdd,weightAdd);



          }
          //创建
          else {


              Goods goods = new Goods();
              //存在，不管，继续添加goods表

              BeanUtil.copyProperties(goodsCreateParam,goods);
              //设置余量为计划销售数量/重量
              goods.setRemainNumber(goodsCreateParam.getGoodsNumber());
              goods.setRemainWeight(goodsCreateParam.getGoodsWeight());
              goods.setAddTime(now);
              goods.setSuppliersId(supplierId.shortValue());

              //----添加goods表数据--------------------------------------------------
              goodsExtMapper.insertSelective(goods);


          }



        }



        return goodsCreateParams.size();
    }
}
