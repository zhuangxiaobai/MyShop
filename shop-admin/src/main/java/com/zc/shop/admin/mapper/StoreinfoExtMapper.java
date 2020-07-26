package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.StoreinfoMapper;
import com.zc.shop.mbg.po.Storeinfo;
import org.apache.ibatis.annotations.Param;

public interface StoreinfoExtMapper extends StoreinfoMapper {

    Storeinfo selectStoreinfoByUserId(@Param("userId") Integer userId);
}