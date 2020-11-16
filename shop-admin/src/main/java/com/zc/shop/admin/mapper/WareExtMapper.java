package com.zc.shop.admin.mapper;


import com.zc.shop.mbg.mapper.WareMapper;
import com.zc.shop.mbg.po.Ware;

import java.util.List;

public interface WareExtMapper extends WareMapper {

    List<Ware> selectAllWare();
}