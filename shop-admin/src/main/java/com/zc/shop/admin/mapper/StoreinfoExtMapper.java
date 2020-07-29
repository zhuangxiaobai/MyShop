package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.StoreinfoDetailParam;
import com.zc.shop.admin.dto.StoreinfoListParam;
import com.zc.shop.admin.vo.GoodsAllInfoVo;
import com.zc.shop.admin.vo.StoreinfoBuyDetailsVo;
import com.zc.shop.mbg.mapper.StoreinfoMapper;
import com.zc.shop.mbg.po.Storeinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreinfoExtMapper extends StoreinfoMapper {

    Storeinfo selectStoreinfoByUserId(@Param("userId") Integer userId);

    List<Storeinfo> selectStoreinfoList(StoreinfoListParam storeinfoListParam);

    int selectStoreinfoListNum( StoreinfoListParam storeinfoListParam);

    List<GoodsAllInfoVo> StoreinfoDetails(StoreinfoDetailParam storeinfoDetailParam);

    int StoreinfoDetailsNum(StoreinfoDetailParam storeinfoDetailParam);
}