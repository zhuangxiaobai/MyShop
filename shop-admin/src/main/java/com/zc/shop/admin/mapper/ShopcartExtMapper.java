package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.ShopcartMapper;
import com.zc.shop.mbg.po.Shopcart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopcartExtMapper extends ShopcartMapper {

    List<Shopcart> selectByUserId(@Param("userId") Integer userId);
}