package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.ChuKuXiaoShouParam;
import com.zc.shop.admin.dto.RuKuCaiGouParam;
import com.zc.shop.admin.dto.RuKuChuKuParam;
import com.zc.shop.admin.vo.RuKuCaiGouChukuXiaoShouVo;
import com.zc.shop.admin.vo.RuKuChuKuVo;
import com.zc.shop.mbg.mapper.InventoryLogMapper;

import java.util.List;


public interface InventoryLogExtMapper extends InventoryLogMapper {


    List<RuKuChuKuVo> selectRuKuByParam(RuKuChuKuParam ruKuChuKuParam);

    Integer selectRuKuNumByParam(RuKuChuKuParam ruKuChuKuParam);

    List<RuKuChuKuVo> selectChuKuByParam(RuKuChuKuParam ruKuChuKuParam);

    Integer selectChuKuNumByParam(RuKuChuKuParam ruKuChuKuParam);

    List<RuKuCaiGouChukuXiaoShouVo> selectRuKuCaiGouList(RuKuCaiGouParam ruKuCaiGouParam);

    List<RuKuCaiGouChukuXiaoShouVo> selectChuKuXiaoShouList(ChuKuXiaoShouParam chuKuXiaoShouParam);
}
