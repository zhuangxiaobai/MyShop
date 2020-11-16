package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.*;
import com.zc.shop.mbg.po.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    int createInventory(List<Inventory> inventoryList, Integer userId);

    int createChuku(List<ChuKuParam> chuKuParams, Integer userId);

    Map inventoryList(InventorySelectParam inventorySelectParam, Integer userId);

    Map ruKuList(RuKuChuKuParam ruKuChuKuParam, Integer userId);

    Map chuKuList(RuKuChuKuParam ruKuChuKuParam, Integer userId);

    Map ruKuCaiGouList(RuKuCaiGouParam ruKuCaiGouParam, Integer userId);

    Map chuKuXiaoShouList(ChuKuXiaoShouParam chuKuXiaoShouParam, Integer userId);

    int inventoryAddGoods(List<GoodsCreateParam> goodsCreateParams, Integer userId);
}
