package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Order;
import com.zc.shop.mbg.po.Settlement;
import lombok.Data;

@Data
public class RuKuCaiGouChukuXiaoShouVo {

    private Order order;


    private Goods goods;


    private Settlement settlement;



}
