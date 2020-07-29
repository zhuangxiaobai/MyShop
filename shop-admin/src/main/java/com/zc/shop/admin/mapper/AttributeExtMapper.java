package com.zc.shop.admin.mapper;

import com.zc.shop.admin.vo.AttributeVo;
import com.zc.shop.mbg.mapper.AttributeMapper;
import com.zc.shop.mbg.po.Attribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttributeExtMapper extends AttributeMapper {

    /**
     *  传入品种，品名，规格，参数查看存不存在
     * @return  存在的那条的id
     */
    Short selectAttributeCountExistByCreate(@Param("pingZhong") String pingZhong, @Param("attribute") Attribute attribute);


    /**
     *  传入品种
     * @return  此品种的id
     */
    Short selectIdByAttrName(@Param("pingZhong") String pingZhong);


    /**
     *
     * @return  所有品种集合
     */
    List<String> selectPingZhongAll();

    /**
     *
     * @return  品种下所有品名集合
     */
    List<Attribute> ListByPingZhong(@Param("pingZhong") String pingZhong);



    List<AttributeVo> AllAttributeVo();
}