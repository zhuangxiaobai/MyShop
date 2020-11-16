package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.*;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailAllVo {



     private Order order;

     //和order中的remainnumber一样，为了方便用
     private Integer number;

     private Goods goods;


     private Settlement settlement;


     private Regiinvoicedetail regiinvoicedetail;


     private List<Lading> ladingList;

}
