package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.GoodsSelectGaiParam;
import com.zc.shop.admin.dto.GoodsSellListParam;
import com.zc.shop.admin.vo.GoodsAllInfoVo;
import com.zc.shop.mbg.mapper.GoodsMapper;
import com.zc.shop.mbg.po.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsExtMapper extends GoodsMapper {

    int updateGoodsNum(@Param("goodId") Integer goodId, @Param("num") Short buyNum,@Param("remainWeight")BigDecimal remainWeight);

    int updateGoodsNumPlus(@Param("goodId") Integer goodId, @Param("remainNum") Short remainNum,@Param("remainWeight") BigDecimal remainWeight);

    List<Goods> selectGoodsBuSupplierId(@Param("supplierId") Integer supplierId);

    List<String> selectAttr1All();

    List<String> selectAttr2All();

    List<Goods> selectGoodsAllIndex(GoodsSelectGaiParam goodsSelectGaiParam);

    int selectGoodsAllIndexNum(GoodsSelectGaiParam goodsSelectGaiParam);

    List<Goods> selectGoodsSelllist(GoodsSellListParam goodsSellListParam);

   int selectGoodsSelllistNum(GoodsSellListParam goodsSellListParam);

    Goods selectByNameGuigeCaizhi( @Param("supplierId") Short supplierId,@Param("name") String name, @Param("guige") String guige, @Param("caizhi") String caizhi);

    int updateGoodsNumAll(@Param("goodId") Integer goodId, @Param("numberAdd") Short numberAdd,@Param("weightAdd") BigDecimal weightAdd);

    // int updateGoodsIsOnSale(GoodsUpdateOnSaleParam goodsUpdateOnSaleParam);
}