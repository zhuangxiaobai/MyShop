package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Goods;
import com.zc.shop.mbg.po.Storeinfo;
import com.zc.shop.mbg.po.Ware;
import lombok.Data;

@Data
public class GoodsAllInfoVo {


      private Goods goods;

      private AttributeVo  attributeVo;

      private Ware ware;

      private UsersVo usersVo;

      private Storeinfo storeinfo;

}
