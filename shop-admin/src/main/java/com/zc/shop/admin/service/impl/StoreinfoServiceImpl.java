package com.zc.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zc.shop.admin.dto.StoreinfoCreateParam;
import com.zc.shop.admin.dto.StoreinfoDetailParam;
import com.zc.shop.admin.dto.StoreinfoListParam;
import com.zc.shop.admin.mapper.GoodsExtMapper;
import com.zc.shop.admin.mapper.StoreinfoExtMapper;
import com.zc.shop.admin.mapper.WareExtMapper;
import com.zc.shop.admin.service.StoreinfoService;
import com.zc.shop.admin.vo.GoodsAllInfoVo;
import com.zc.shop.admin.vo.StoreinfoBuyDetailsVo;
import com.zc.shop.admin.vo.StoreinfoListVo;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Storeinfo;
import com.zc.shop.mbg.po.Ware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StoreinfoServiceImpl implements StoreinfoService {

    @Autowired
    private StoreinfoExtMapper storeinfoExtMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;

    @Autowired
    private WareExtMapper wareExtMapper;


    @Override
    public int create(StoreinfoCreateParam storeinfoCreateParam, Integer userId) {

        Storeinfo storeinfo = new Storeinfo();
        //查是否已经拥有了店铺
        Storeinfo storeinfoExist = storeinfoExtMapper.selectStoreinfoByUserId(userId);
        if(storeinfoExist !=null){
            throw new BusinessException(ResultCode.STOREINFOEXIST);
        }


        BeanUtil.copyProperties(storeinfoCreateParam,storeinfo);
        LocalDateTime now = LocalDateTime.now();
        storeinfo.setCreatedAt(now);
        storeinfo.setUpdatedAt(now);
        storeinfo.setUserId(userId);
        return storeinfoExtMapper.insertSelective(storeinfo);
    }

    @Override
    public Storeinfo StoreinfoByUserId(Integer userId) {
        return storeinfoExtMapper.selectStoreinfoByUserId(userId);
    }

    @Override
    public StoreinfoListVo StoreinfoList(StoreinfoListParam storeinfoListParam) {


        //分页查询处理
        Integer startPage = storeinfoListParam.getPageParam().getStartPage();
        Integer pageSize = storeinfoListParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        storeinfoListParam.getPageParam().setStartPage(start);


        StoreinfoListVo storeinfoListVo  = new StoreinfoListVo();

        List<Storeinfo> storeinfos  = storeinfoExtMapper.selectStoreinfoList(storeinfoListParam);
        int total  = storeinfoExtMapper.selectStoreinfoListNum(storeinfoListParam);


        storeinfoListVo.setStoreinfos(storeinfos);
        storeinfoListVo.setTotal(total);


        return storeinfoListVo;
    }

    @Override
    public StoreinfoBuyDetailsVo StoreinfoDetails(StoreinfoDetailParam storeinfoDetailParam) {


        //分页查询处理
        Integer startPage = storeinfoDetailParam.getPageParam().getStartPage();
        Integer pageSize = storeinfoDetailParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize;
        storeinfoDetailParam.getPageParam().setStartPage(start);


        StoreinfoBuyDetailsVo storeinfoBuyDetailsVo = new StoreinfoBuyDetailsVo();
        List<GoodsAllInfoVo> goodsAllInfoVoList =   storeinfoExtMapper.StoreinfoDetails(storeinfoDetailParam);
        int goodsAllInfoVoListNum =   storeinfoExtMapper.StoreinfoDetailsNum(storeinfoDetailParam);


        storeinfoBuyDetailsVo.setGoodsAllInfoVoList(goodsAllInfoVoList);
        storeinfoBuyDetailsVo.setTotal(goodsAllInfoVoListNum);

        return storeinfoBuyDetailsVo;
    }

    @Override
    public Map storeinfoDetailsSelectParam(Short storeinfoId) {


        Map<String,Object> map  = new HashMap();


        Set<String> attrNameSet = new HashSet<>();
        Set<String> changJiaSet = new HashSet<>();
        List<Ware> wareList = new ArrayList<>();


        //通过店铺id查卖家id
        Storeinfo storeinfo = storeinfoExtMapper.selectByPrimaryKey(storeinfoId);
        Integer supplierId  = storeinfo.getUserId();


        //通过卖家id查商品表集合
         List<Goods>  goodsList = goodsExtMapper.selectGoodsBuSupplierId(supplierId);



        //通过循环商品表集合去获取仓库对象
        for(Goods goods:goodsList){

            Ware ware = wareExtMapper.selectByPrimaryKey(goods.getWareId().shortValue());



            attrNameSet.add(goods.getGoodsName());
            changJiaSet.add(goods.getAttr1());

            wareList.add(ware);


        }

        map.put("attrNameSet",attrNameSet);
        map.put("changJiaSet",changJiaSet);
        map.put("wareList",wareList);







        return map;
    }
}
