package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.InventorySelectParam;
import com.zc.shop.mbg.mapper.InventoryMapper;
import com.zc.shop.mbg.po.Inventory;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryExtMapper extends InventoryMapper {
    Inventory selectByNameGuigeCaizhi(@Param("name") String name, @Param("guige") String guige, @Param("caizhi") String caizhi);

    int updateNumWeight(@Param("inventoryExistId") Long inventoryExistId, @Param("number") Integer number,@Param("weight") BigDecimal weight);

    List<Inventory> selectByInventoryParam(InventorySelectParam inventorySelectParam);

    Integer selectByInventoryParamNum(InventorySelectParam inventorySelectParam);
}
